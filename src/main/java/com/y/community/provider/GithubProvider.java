package com.y.community.provider;

import com.alibaba.fastjson.JSON;
import com.y.community.dto.AccessTokenDTO;
import com.y.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
//        System.out.println("accessTokenDTO:" + JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
//        System.out.println("request:" + "getAccessToken: " + request);

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
//            System.out.println("token_string:" + string);
//            System.out.println("token:" + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //github新版请求方式
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
//               .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        System.out.println("request:" + "getUser:" + request);
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println("User : " + string);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}