package cn.ulyer.demo.dao;

import cn.ulyer.demo.entity.User;
import cn.ulyer.orm.annotation.Insert;

import java.util.List;

public interface UserMapper {

    @Insert(sql = "insert into user values(#{id},#{name})")
    int saveUser(User user);


    User getById(String id);

}