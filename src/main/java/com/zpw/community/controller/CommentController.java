package com.zpw.community.controller;

import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.dto.CommentDTO;
import com.zpw.community.dto.ResultDTO;
import com.zpw.community.model.Comment;
import com.zpw.community.model.User;
import com.zpw.community.sevice.CommentService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/13-22:29
 */
@Controller
public class CommentController {
  @Autowired
  CommentService commentService;

  @RequestMapping(value = "/comment",method = RequestMethod.POST)
  @ResponseBody
  public Object addComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
    User user = (User)request.getSession().getAttribute("user");
    if(user==null){
      return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
    }
    Comment comment = new Comment();
    comment.setGmtCreate(System.currentTimeMillis());
    comment.setGmtModified(comment.getGmtCreate());
    comment.setContent(commentDTO.getContent());
    comment.setParentId(commentDTO.getParentId());
    comment.setType(commentDTO.getType());
    comment.setCommentator(user.getId());
    commentService.addComment(comment);
    return  ResultDTO.okOf();
  }
}
