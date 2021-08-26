package com.y.community.controller;

import com.y.community.controller.mapper.UserMapper;
import com.y.community.dto.AccessTokenDTO;
import com.y.community.dto.GithubUser;
import com.y.community.model.User;
import com.y.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redircetUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redircetUri);
        accessTokenDTO.setState(state);
        System.out.println("==code==" + code);
        System.out.println("==state==" + state);

        System.out.println("==accessTokenDTO==" + accessTokenDTO.toString());

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("==accessToken==" + accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println("=user=" + githubUser);
        String name = githubUser.getName();
        if (name == null) {
//            有可能账号未设置name，取login作为其name
           name = githubUser.getLogin();
        }

        if (githubUser != null) {
            User user = new User();
//            不可变的通用唯一标识符 (UUID)
            user.setToken(UUID.randomUUID().toString());
            user.setName(name);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            // 登录成功， 写session和cookie
            request.getSession().setAttribute("user", githubUser);

            System.out.println(name);
            return "redirect:/";
        } else {
            return "redirect:/";
        }

    }
}
