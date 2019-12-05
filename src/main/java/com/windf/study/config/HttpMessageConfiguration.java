package com.windf.study.config;

import com.windf.study.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

/**
 * 添加自描述消息处理
 */
@Configuration
public class HttpMessageConfiguration implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesPersonHttpMessageConverter());
    }

    /**
     * 自描述消息处理
     * 这里写死了User，当然也可以写活了，只是Properties和对象的反射转换
     */
    private static class PropertiesPersonHttpMessageConverter extends AbstractHttpMessageConverter<User> {

        public PropertiesPersonHttpMessageConverter(){
            super(MediaType.valueOf("application/properties"));
            setDefaultCharset(Charset.forName("UTF-8"));
        }

        @Override
        protected boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(User.class);
        }

        /**
         * 读取请求内容到Properties中，转换为实体对象
         * @param aClass
         * @param httpInputMessage
         * @return
         * @throws IOException
         * @throws HttpMessageNotReadableException
         */
        @Override
        protected User readInternal(Class<? extends User> aClass,
                HttpInputMessage httpInputMessage)
                    throws IOException, HttpMessageNotReadableException {

            InputStream inputStream = httpInputMessage.getBody();

            // 将请求中的内容转化成Properties
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,getDefaultCharset()));

            // 将properties 内容转化到 Person 对象字段中
            User user = new User();
            user.setId(Long.valueOf(properties.getProperty("user.id")));
            user.setName(properties.getProperty("user.name"));
            return user;
        }

        @Override
        protected void writeInternal(User user,
                HttpOutputMessage httpOutputMessage)
                    throws IOException, HttpMessageNotWritableException {
            OutputStream outputStream = httpOutputMessage.getBody();

            Properties properties = new Properties();

            properties.setProperty("user.id",String.valueOf(user.getId()));
            properties.setProperty("user.name",user.getName());

            properties.store(new OutputStreamWriter(
                            outputStream,
                            getDefaultCharset()),
                    "Written by web server");
        }
    }
}
