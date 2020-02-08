package com.zpw.community.dto;

import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/29-18:42
 */

@Data
public class AccessTokenDTO {
  private String client_id;

  private String client_secret;

  private String code;

  private String redirect_url;

  private String state;

}
