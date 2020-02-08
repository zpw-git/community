package com.zpw.community.sevice.impl;

import com.zpw.community.Dao.UserDao;
import com.zpw.community.model.User;
import com.zpw.community.sevice.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-11:11
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public void addUser(User user) {
    userDao.insertSelective(user);
  }

  @Override
  public User findUserByToken(String Token) {
    Example example = new Example(User.class);
    Criteria criteria = example.createCriteria();
    criteria.andEqualTo("token",Token);
    List<User> users = userDao.selectByExample(example);
    System.out.println(users.get(0));
    return users.get(0);
  }
}
