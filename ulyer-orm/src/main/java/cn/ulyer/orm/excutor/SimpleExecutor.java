package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:03 2021/3/20
 */

@Getter
@Setter
public class SimpleExecutor extends AbstractExecutor {


    public SimpleExecutor (Connection connection,OrmConfiguration ormConfiguration){
        super(connection,ormConfiguration);
    }






}
