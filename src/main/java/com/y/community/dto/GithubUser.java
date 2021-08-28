package com.y.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GithubUser {
    //github新版存放name属性
    private String login;
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
