package com.zpw.community.sevice.impl;

import com.zpw.community.Dao.CommentDao;
import com.zpw.community.Dao.QuestionDao;
import com.zpw.community.Dao.UserDao;
import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.Exception.CustomizeException;
import com.zpw.community.dto.CommentListDTO;
import com.zpw.community.enums.CommentTypeEnum;
import com.zpw.community.model.Comment;
import com.zpw.community.model.Question;
import com.zpw.community.model.User;
import com.zpw.community.sevice.CommentService;
import com.zpw.community.sevice.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/13-22:24
 */
@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentDao commentDao;
  @Autowired
  QuestionDao questionDao;
  @Autowired
  UserDao userDao;

  @Override
  @Transactional
  public void addComment(Comment comment) {
    if(comment.getParentId()==null || comment.getParentId()==0){
      throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_PARENT);
    }
    if(comment.getType()==null || !CommentTypeEnum.isExit(comment.getType().intValue())){
      throw new CustomizeException(CustomizeErrorCode.TYPE_ERROR);
    }
    if(comment.getType().intValue()==CommentTypeEnum.QUESTION.getType()){
      //回复问题
      Question question = questionDao.selectByPrimaryKey(comment.getParentId());
      if(question!=null){
        commentDao.insertSelective(comment);
        questionDao.addCommentCount(comment.getParentId());
      }else {
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
    }else {
      //回复评论
      Comment dbcomment = commentDao.selectByPrimaryKey(comment.getParentId());
      if(dbcomment!=null){
        commentDao.insertSelective(comment);
      }else {
        throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
      }
    }


  }

  /**
   * 查询所有一级评论
   * @param id
   * @return
   */
  @Override
  public List<CommentListDTO> queryAllComment(Long id) {
    Comment comment = new Comment();
    comment.setParentId(id);
    List<Comment> comments = commentDao.select(comment);
    if(comments.size()==0){
      return new ArrayList<>();
    }
    List<Long> collect = comments.stream().map(m -> m.getCommentator())
        .distinct().collect(Collectors.toList());
    Example example = new Example(User.class);
    Criteria criteria = example.createCriteria();
    criteria.andIn("id",collect);
    List<User> userList = userDao.selectByExample(example);
    Map<Long, User> userMap = userList.stream()
        .collect(Collectors.toMap(user -> user.getId(), user -> user));
    List<CommentListDTO> commentListDTOS = comments.stream().map(c -> {
      CommentListDTO commentListDTO = new CommentListDTO();
      commentListDTO.setComment(c);
      commentListDTO.setUser(userMap.get(c.getCommentator()));
      return commentListDTO;
    }).collect(Collectors.toList());
    return commentListDTOS;
  }
}
