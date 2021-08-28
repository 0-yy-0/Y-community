package com.y.community.dto;

import lombok.Data;

//两个参数以上最好封装成对象
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
