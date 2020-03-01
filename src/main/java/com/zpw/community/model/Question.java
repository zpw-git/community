package com.zpw.community.model;

import javax.persistence.Id;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-18:27
 */

@Data
public class Question {
  @Id
  private Long id;
  private String title;
  private String tag;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private String creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;

}
