package com.zpw.community.dto;

import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/14-11:39
 */
@Data
public class CommentDTO {

  Long parentId;

  Long type;

  String content;

  Long commentator;

}
