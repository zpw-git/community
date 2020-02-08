package com.zpw.community.dto;

import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/30-9:41
 */

@Data
public class GithubUser {

  private String name;

  private Long id;

  private String bio;

  private String avatar_url;

}
