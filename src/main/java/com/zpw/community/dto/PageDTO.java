package com.zpw.community.dto;

import java.util.List;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/7-12:19
 */
@Data
public class PageDTO {

  private Integer curr;

  private Integer pages;

  private Integer count;

  List<QuestionDTO> data;
}
