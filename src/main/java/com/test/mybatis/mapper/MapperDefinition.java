package com.test.mybatis.mapper;

import lombok.Data;

import java.util.List;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:54 2021/3/20
 */
@Data
public class MapperDefinition {

    private String namespace;

    private List<MapperMethod> mapperMethod;
}
