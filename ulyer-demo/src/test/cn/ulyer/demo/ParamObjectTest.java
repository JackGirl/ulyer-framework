package cn.ulyer.demo;

import cn.ulyer.demo.entity.User;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.parameter.ParameterObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ParamObjectTest {

    @Test
    public void t()  {
        User u= new User();
        u.setId("1");u.setName("231");
       ParameterObject parameterObject = new ParameterObject(u,User.class,null);
        ParameterObject object = ParameterObject.newParameter(new MapperMethod(),new Object[]{parameterObject});
        System.out.println((String) object.getParameterByName("name"));
    }

}
