package com.y.community.dto;

import org.springframework.stereotype.Component;

@Component
public class GithubUser {
    //github新版存放name属性
    private String login;
    private String name;
    private Long id;
    private String bio;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getName() {
        return name;
    }

    public void setName(String login) {
        this.name = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
