package com.zpw.community.dto;

import com.zpw.community.model.Question;
import com.zpw.community.model.User;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/4-17:36
 */
@Data
public class QuestionDTO {

  private Question question;

  private User user;
}
