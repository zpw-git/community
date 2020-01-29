package com.zpw.community.controller;

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

  @GetMapping("/hello")
  public String hello(@RequestParam(name = "name") String name , Model model){

    model.addAttribute("name",name);
    return "index";
  }

}
