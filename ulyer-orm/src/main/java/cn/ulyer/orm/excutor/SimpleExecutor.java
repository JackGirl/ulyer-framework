package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.utils.LogUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:03 2021/3/20
 */

@Getter
@Setter
public class SimpleExecutor extends AbstractExecutor {


    public SimpleExecutor (OrmConfiguration ormConfiguration){
        super(ormConfiguration);
    }

    @Override
    public PreparedStatement prepare(Connection connection, MapperMethod mapperMethod, Map<String, Object> params) {
        try {
            return   connection.prepareStatement(mapperMethod.getSql().replaceAll("\\{(.+?)\\}"," ? "));
        } catch (SQLException e) {
            LogUtils.error(e);
            return null;
        }
    }




}
