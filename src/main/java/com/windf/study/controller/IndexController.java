package com.windf.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/404.html")
    public Map<String, Object> errorPage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        result.put("code", response.getStatus());
        result.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));

        return result;
    }
}
