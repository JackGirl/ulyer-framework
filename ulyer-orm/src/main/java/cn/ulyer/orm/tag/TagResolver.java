package cn.ulyer.orm.tag;

import cn.ulyer.orm.mapper.MapperMethod;

import java.util.Map;

/**
 * 标签解析转换  <if></if>  <when></when>
 */
public interface TagResolver {

    String resolverDynamicTag(MapperMethod mapperMethod, Map<String,Object> params);

}
