package com.windf.study.controller;

import com.windf.study.domain.User;
import com.windf.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }

    @PostMapping(value = "/user/json/to/properties",
            produces = "application/properties" // 响应类型，如果客户端Accept为这个类型，这不需要再这里写死，否则返回默认的json
    )
    public User personJsonToProperties(@RequestBody User user) {
        // @RequestBody 的内容是 JSON
        // 响应的内容是 Properties
        return user;
    }

    @PostMapping(value = "/user/properties/to/json",
            consumes = "application/properties", // 请求类型 // Content-Type
            produces =  MediaType.APPLICATION_JSON_VALUE// 响应类型
    )
    public User personPropertiesToJson(@RequestBody User user) {
        // @RequestBody 的内容是 Properties
        // 响应的内容是 JSON
        return user;
    }

    @PostMapping(value = "/user/switch")
    public User personJsonToPropertiesAuto(@RequestBody User user) {
        // 当然这里可以全部省略全部的，只是根据请求的Content-Type和Accept来实现自动转换
        return user;
    }
}
