package com.zpw.community.sevice;

import com.zpw.community.dto.GithubUser;
import com.zpw.community.model.User;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-11:10
 */
public interface UserService {

  void addUser(User user);

  User findUserByToken(String Token);

  User findUserByAccountId(String accountId);

  User createOrUpdate(GithubUser githubUser);
}
