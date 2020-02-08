package com.zpw.community.controller;

import com.zpw.community.dto.AccessTokenDTO;
import com.zpw.community.dto.GithubUser;
import com.zpw.community.model.User;
import com.zpw.community.provider.GithubProvider;
import com.zpw.community.sevice.UserService;
import java.lang.ProcessBuilder.Redirect;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/29-17:57
 */
@Controller
public class AuthotizeController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private GithubProvider githubProvider;
  @Autowired
  private UserService userService;

  @Value("${github.client.id}")
  private String client_id;
  @Value("${github.client.secret}")
  private String client_secret;
  @Value("${github.client.uri}")
  private String uri;


  @GetMapping("/gitlogin")
  public String login(){
    String url = "https://github.com/login/oauth/authorize?client_id=5f57e6278bddc120b3b1&redirect_id=http://localhost:8887/callback&scope=user&state=1";
    return restTemplate.getForObject(url,String.class);
  }

  @GetMapping("/callback")
  public String callback(@RequestParam (name = "code") String code,
                         @RequestParam (name = "state") String state,
                          HttpServletRequest request,
                          HttpServletResponse response){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(client_id);
    accessTokenDTO.setClient_secret(client_secret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_url(uri);
    accessTokenDTO.setState(state);
    String accesstoken = githubProvider.getAccessTokenDTO(accessTokenDTO);
    GithubUser githubUser = githubProvider.githubUser(accesstoken);
    if(githubUser!=null){
      User user = new User();
      user.setAccountId(String.valueOf(githubUser.getId()));
      user.setName(githubUser.getName());
      user.setToken(UUID.randomUUID().toString());
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      user.setAvatarUrl(githubUser.getAvatar_url());
      userService.addUser(user);
      Cookie cookie = new Cookie("githubtoken", user.getToken());
      cookie.setMaxAge(3600);
      response.addCookie(cookie);

    }else {

    }
    return "redirect:/";
  }
}
