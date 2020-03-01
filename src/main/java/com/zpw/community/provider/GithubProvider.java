package com.zpw.community.provider;

import com.alibaba.fastjson.JSON;
import com.zpw.community.dto.AccessTokenDTO;
import com.zpw.community.dto.GithubUser;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/29-18:41
 */

@Component
public class GithubProvider {

  public String getAccessTokenDTO(AccessTokenDTO accessTokenDTO){
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String json = com.alibaba.fastjson.JSON.toJSONString(accessTokenDTO);
    RequestBody body = RequestBody.create(mediaType,json);
    Request request = new Request.Builder()
        .url("https://github.com/login/oauth/access_token")
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      String string = response.body().string();
      String[] split = string.split("&");
      String s = split[0];
      String[] split1 = s.split("=");
      return split1[1];
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;

  }

  public GithubUser githubUser(String accessToken){
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("https://api.github.com/user?access_token="+accessToken)
        .build();
    try {
      Response response = client.newCall(request).execute();
      String string = response.body().string();
      GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
      return githubUser;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }



}
