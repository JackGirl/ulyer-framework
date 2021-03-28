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
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("user",u);
        ParameterObject parameterObject = ParameterObject.newParameter(new MapperMethod(),new Object[]{dataMap});
        System.out.println((String) parameterObject.getParameterByName("user.name"));
    }

}
