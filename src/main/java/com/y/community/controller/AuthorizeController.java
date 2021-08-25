package com.y.community.controller;

import com.y.community.dto.AccessTokenDTO;
import com.y.community.dto.GithubUser;
import com.y.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
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
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println("=user=" + user);
        //github新版存放name属性
        String name = user.getName();
        if (name == null) {
            name = user.getLogin();
        }
        System.out.println(name);
//        System.out.println(user.getName());
        return "index";
    }
}
