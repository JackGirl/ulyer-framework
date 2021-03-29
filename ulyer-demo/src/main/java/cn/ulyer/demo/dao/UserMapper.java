package cn.ulyer.demo.dao;

import cn.ulyer.demo.entity.User;
import cn.ulyer.orm.annotation.Insert;
import cn.ulyer.orm.annotation.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Insert(sql = "insert into user values(#{id},#{name})")
    int saveUser(User user);


    User getById(String id);


    @Select(sql = "select * from user ")
    List<Map> listUser();
}