package com.zpw.community.model;

import javax.persistence.Id;
import lombok.Data;

@Data
public class Comment {

  @Id
  private Long id;
  private Long parentId;
  private Long type;
  private Long commentator;
  private Long gmtCreate;
  private Long gmtModified;
  private Long likeCount;
  private String content;
}
