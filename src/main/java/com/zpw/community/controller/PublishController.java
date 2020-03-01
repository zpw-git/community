package com.zpw.community.controller;

import com.zpw.community.model.Question;
import com.zpw.community.sevice.QuestionService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-21:11
 */
@Controller
public class PublishController {

  @Autowired
  private QuestionService questionService;

  @PostMapping("/addpublish")
  @ResponseBody
  public Map<String,Object> publishQuestion(Question question){
    if(question.getId()==null){
      question.setGmtCreate(System.currentTimeMillis());
      question.setGmtModified(question.getGmtCreate());
      question.setLikeCount(0);
      question.setCommentCount(0);
      question.setViewCount(0);
      questionService.addQuestion(question);
      Map<String, Object> map = new HashMap<>();
      map.put("status",true);
      map.put("msg","成功添加");
      return map;
    }else{
      question.setGmtModified(System.currentTimeMillis());
      questionService.updateQuestion(question);
      Map<String, Object> map = new HashMap<>();
      map.put("status",true);
      map.put("msg","修改成功");
      return map;
    }



  }

//@PostMapping("/")
//@ResponseBody
//  public PageDTO page(PageDTO page,Model model){
//    if(page!=null){
//      PageDTO pageDTO = questionService.pageQuery(page.getCurr(), page.getPages());
//      model.addAttribute("questions", pageDTO.getData());
//      return pageDTO;
//    }
//    return null;
//  }
}
