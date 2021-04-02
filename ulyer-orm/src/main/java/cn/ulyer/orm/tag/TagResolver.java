package cn.ulyer.orm.tag;

import cn.ulyer.orm.mapper.MapperWrapper;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * 标签解析转换  <if></if>  <when></when>
 */
public interface TagResolver {

    void resolverDynamicTag(MapperWrapper mapperWrapper, Element node) throws DocumentException;


}
