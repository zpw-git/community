package com.zpw.community.sevice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zpw.community.Dao.QuestionDao;
import com.zpw.community.Dao.UserDao;
import com.zpw.community.dto.PageDTO;
import com.zpw.community.dto.QuestionDTO;
import com.zpw.community.model.Question;
import com.zpw.community.model.User;
import com.zpw.community.sevice.QuestionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-21:05
 */
@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  private QuestionDao questionDao;
  @Autowired
  private UserDao userDao;

  @Override
  public void addQuestion(Question question) {
    questionDao.insert(question);
  }

  @Override
  public List<QuestionDTO> queryAllQuestioin() {
    List<QuestionDTO> questionDTOList = new ArrayList<>();
    List<Question> questions = questionDao.selectAll();
    for(Question question : questions){
      User user = new User();
      user.setAccountId(question.getCreator());
      List<User> userList = userDao.select(user);
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setQuestion(question);
      questionDTO.setUser(userList.get(0));
      questionDTOList.add(questionDTO);
    }
    return questionDTOList;
  }

  @Override
  public QuestionDTO queryById(Integer id) {
    Question question = new Question();
    question.setId(id);
    Question qt = questionDao.selectOne(question);
    User user = new User();
    user.setAccountId(question.getCreator());
    List<User> select = userDao.select(user);
    QuestionDTO questionDTO = new QuestionDTO();
    questionDTO.setUser(select.get(0));
    questionDTO.setQuestion(qt);
    return questionDTO;
  }

  @Override
  public PageDTO pageQuery(Integer curr, Integer num) {
    Integer integer = questionDao.selectCount();
    PageDTO pageDTO = new PageDTO();
    QuestionDTO questionDTO = new QuestionDTO();
    List<QuestionDTO> questionDTOList = new ArrayList<>();
    PageHelper.startPage(curr, num);
    List<Question> questions = questionDao.selectAll();
    for (Question question:questions){
      User user = new User();
      user.setAccountId(question.getCreator());
      List<User> userList = userDao.select(user);
      questionDTO.setQuestion(question);
      questionDTO.setUser(userList.get(0));
      questionDTOList.add(questionDTO);
    }
    pageDTO.setData(questionDTOList);

    pageDTO.setCount(integer);
    return pageDTO;
  }
}
