package com.zpw.community.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.zpw.community.Dao.QuestionDao;
import com.zpw.community.Dao.UserDao;
import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.Exception.CustomizeException;
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
  public void updateViewCount(Long id) {
    questionDao.addViewCount(id);
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

  public Question queryQuestionById(Long id){
    Question question = new Question();
    question.setId(id);
    Question qt = questionDao.selectOne(question);
    return qt;
  }

  @Override
  public void updateQuestion(Question question) {
     questionDao.updateByPrimaryKey(question);
  }

  @Override
  public QuestionDTO queryById(Long id) {
    Question question = new Question();
    question.setId(id);
    Question qt = questionDao.selectOne(question);
    if(qt==null){
      throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
    }
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
    Integer integer = questionDao.selectCount(new Question());
    PageDTO pageDTO = new PageDTO();

    List<QuestionDTO> questionDTOList = new ArrayList<>();
    PageHelper.startPage(curr, num);
    List<Question> questions = questionDao.selectAll();
    for (Question question:questions){
      User user = new User();
      user.setAccountId(question.getCreator());
      List<User> userList = userDao.select(user);
      QuestionDTO questionDTO = new QuestionDTO();
      questionDTO.setQuestion(question);
      questionDTO.setUser(userList.get(0));
      questionDTOList.add(questionDTO);
    }
    pageDTO.setData(questionDTOList);

    pageDTO.setCount(integer);
    return pageDTO;
  }

  @Override
  public List<Question> queryByCreator(String Creator) {
    Question question = new Question();
    question.setCreator(Creator);
    List<Question> select = questionDao.select(question);
    return select;
  }
}
