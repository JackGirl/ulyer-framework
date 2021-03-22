package com.test.mybatis.tag;

import com.test.mybatis.mapper.Mapper;

import java.util.Map;

/**
 * 标签解析转换  <if></if>  <when></when>
 */
public interface TagResolver {

    String resolverDynamicTag(Mapper mapper, Map<String,Object> params);

}
