package com.zpw.community.model;

import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-10:44
 */
@Data
@Table
public class User {

  @Id
  private Integer id;

  private String name;

  private String accountId;

  private String token;

  private Long gmtCreate;

  private Long gmtModified;

  private String avatarUrl;

}
