package com.zpw.community.controller;

import com.zpw.community.model.Question;
import com.zpw.community.model.User;
import com.zpw.community.sevice.QuestionService;
import com.zpw.community.sevice.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/10-10:21
 */
@Controller
public class UserController {

  @Autowired
  private QuestionService questionService;
  @Autowired
  private UserService userService;

  @GetMapping("user/{path}/{accountId}")
  public String userHome(@PathVariable(name = "path") String path,
      @PathVariable(name = "accountId") String accountId,
      Model model) {
    User userByAccountId = userService.findUserByAccountId(accountId);
    List<Question> questions = questionService.queryByCreator(accountId);
    model.addAttribute("questions",questions);
    model.addAttribute("user",userByAccountId);
    return "user/" + path;
  }
  @GetMapping("user/{path}")
  public String user(@PathVariable(name = "path") String path){
    return "user/" + path;
  }

  @GetMapping("edit")
  public String edit(@RequestParam(name = "questionId")String questionId,Model model){
    Long i = Long.parseLong(questionId);
    Question question = questionService.queryQuestionById(i);
    model.addAttribute("questionId",question.getId());
    model.addAttribute("description",question.getDescription());
    model.addAttribute("tag",question.getTag());
    model.addAttribute("title",question.getTitle());
    return "publish";
  }
}
