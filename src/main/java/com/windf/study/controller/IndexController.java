package com.windf.study.controller;

import com.windf.study.core.bean.RestResult;
import com.windf.study.core.exception.UserException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexController {
    @GetMapping("/404.html")
    public RestResult errorPage(HttpServletRequest request, HttpServletResponse response) {
        return RestResult.result(
                response.getStatus(),
                request.getAttribute("javax.servlet.error.request_uri"));
    }

    @GetMapping("/eee")
    public RestResult error() {
        throw new UserException("这是一个故意抛出的异常");
    }
}
