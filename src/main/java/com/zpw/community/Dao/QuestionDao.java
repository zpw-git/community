package com.zpw.community.Dao;

import com.zpw.community.model.Question;
import java.util.List;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-21:02
 */
@Component
public interface QuestionDao extends Mapper<Question> {

    Integer addViewCount(Long id);

    Integer addCommentCount(Long id);
}
