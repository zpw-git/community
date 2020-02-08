package com.zpw.community.Dao;

import com.zpw.community.model.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: zhangpengwei
 * @create: 2020-01-2020/1/31-11:03
 */
@Component
public interface UserDao extends Mapper<User> {


}
