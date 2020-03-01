package com.zpw.community.controller;

import com.zpw.community.Dao.CommentDao;
import com.zpw.community.dto.CommentListDTO;
import com.zpw.community.dto.PageDTO;
import com.zpw.community.dto.QuestionDTO;
import com.zpw.community.model.User;
import com.zpw.community.sevice.CommentService;
import com.zpw.community.sevice.QuestionService;
import com.zpw.community.sevice.UserService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/29-11:45
 */
@Controller
public class HelloController {

  @Autowired
  private UserService userService;
  @Autowired
  private QuestionService questionService;
  @Autowired
  private CommentService commentService;

  @GetMapping("/")
  public String index(HttpServletRequest request, Model model
         ,@RequestParam(name = "curr",defaultValue = "1",required = false) Integer curr
         ,@RequestParam(name="size",defaultValue = "10",required = false)Integer size){

    //分页查询
    PageDTO pageDTO = questionService.pageQuery(curr, size);
    pageDTO.setCurr(curr);
    pageDTO.setPages(size);
    model.addAttribute("pageDTO",pageDTO);
    return "index";
  }


  @GetMapping("/publish")
  public String publish(){
    return "publish";
  }

  @GetMapping("/header")
  public String login(){
    return "header";
  }

  @GetMapping("/footer")
  public String userIndex(){
    return "footer";
  }

  @GetMapping("/detail")
  public String detail(@RequestParam("questionid") String questionid,Model model){
    Long id =Long.parseLong(questionid);
    QuestionDTO questionDTO = questionService.queryById(id);
    questionService.updateViewCount(id);
    List<CommentListDTO> commentListDTOS = commentService.queryAllComment(id);
    model.addAttribute("commentList",commentListDTOS);
    model.addAttribute("question",questionDTO);
    return "detail";
  }

  @GetMapping("loginout")
  public String loginOut(HttpServletRequest request, HttpServletResponse response){
    request.getCookies();
    Cookie cookie = new Cookie("githubtoken", "");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/";
  }


}
