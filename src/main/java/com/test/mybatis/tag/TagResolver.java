package com.test.mybatis.tag;

import com.test.mybatis.mapper.MapperMethod;

import java.util.Map;

/**
 * 标签解析转换  <if></if>  <when></when>
 */
public interface TagResolver {

    String resolverDynamicTag(MapperMethod mapperMethod, Map<String,Object> params);

}
