package com.zpw.community.dto;

import com.zpw.community.model.Comment;
import com.zpw.community.model.User;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/15-19:58
 */
@Data
public class CommentListDTO {

  Comment comment;

  User user;

}
