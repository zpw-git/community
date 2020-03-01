package com.zpw.community.sevice.impl;

import com.zpw.community.Dao.UserDao;
import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.Exception.CustomizeException;
import com.zpw.community.dto.GithubUser;
import com.zpw.community.model.User;
import com.zpw.community.sevice.UserService;
import java.util.List;
import java.util.UUID;
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

  @Autowired
  private UserService userService;

  @Override
  public void addUser(User user) {
    userDao.insertSelective(user);
  }

  @Override
  public User findUserByToken(String Token) {
    Example example = new Example(User.class);
    Criteria criteria = example.createCriteria();
    criteria.andEqualTo("token",Token);
    User users = userDao.selectOneByExample(example);
    return users;
  }

  @Override
  public User findUserByAccountId(String accountId) {
    User user = new User();
    user.setAccountId(accountId);
    List<User> select = userDao.select(user);
    if(select.size()==0){
      throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND.getMessage());
    }
    return select.get(0);
  }

  @Override
  public User  createOrUpdate(GithubUser githubUser) {
    User user = new User();
    user.setAccountId(githubUser.getId().toString());
    User dbUser = userDao.selectOne(user);
    if(dbUser!=null){
      dbUser.setAccountId(String.valueOf(githubUser.getId()));
      dbUser.setName(githubUser.getName());
      dbUser.setToken(UUID.randomUUID().toString());
      dbUser.setGmtModified(System.currentTimeMillis());
      dbUser.setAvatarUrl(githubUser.getAvatar_url());
      userDao.updateByPrimaryKey(dbUser);
      return dbUser;
    }else{
      user.setAccountId(String.valueOf(githubUser.getId()));
      user.setName(githubUser.getName());
      user.setToken(UUID.randomUUID().toString());
      user.setGmtModified(System.currentTimeMillis());
      user.setAvatarUrl(githubUser.getAvatar_url());
      user.setGmtCreate(user.getGmtModified());
      userService.addUser(user);
      return user;
    }

  }
}
