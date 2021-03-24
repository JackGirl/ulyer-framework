package com.test.dao;

import com.test.entity.User;
import cn.ulyer.orm.annotation.Insert;

public interface UserMapper {

    @Insert(sql = "insert into user values({id},{name})")
    int saveUser(User user);



    User getById(String id);

}