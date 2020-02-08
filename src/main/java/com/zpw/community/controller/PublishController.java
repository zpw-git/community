package com.zpw.community.controller;

import com.alibaba.fastjson.JSON;
import com.zpw.community.dto.PageDTO;
import com.zpw.community.model.Question;
import com.zpw.community.sevice.QuestionService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    question.setGmtCreate(System.currentTimeMillis());
    question.setGmtModified(question.getGmtCreate());
    questionService.addQuestion(question);
    Map<String, Object> map = new HashMap<>();
    map.put("status",true);
    map.put("msg","成功添加");
    return map;

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
