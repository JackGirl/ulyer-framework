package com.test.mybatis;

import com.test.mybatis.enums.MapperSqlType;
import lombok.Data;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:54 2021/3/20
 */
@Data
public class MapperSql {

    private String sql;

    private String id;


    private MapperSqlType mapperSqlType;

}
