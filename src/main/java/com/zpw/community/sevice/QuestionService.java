package com.zpw.community.sevice;

import com.zpw.community.dto.PageDTO;
import com.zpw.community.dto.QuestionDTO;
import com.zpw.community.model.Question;
import java.util.List;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-21:03
 */
public interface QuestionService {

  void addQuestion (Question question);

  List<QuestionDTO> queryAllQuestioin();

  QuestionDTO queryById(Integer id);

  PageDTO pageQuery(Integer curr,Integer num);

}
