package com.y.community.controller;

import com.y.community.mapper.UserMapper;
import com.y.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
//        request.getSession().setAttribute("user", user);

        return "index";
    }
}
