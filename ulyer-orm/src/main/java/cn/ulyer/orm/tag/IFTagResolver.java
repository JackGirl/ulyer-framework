package cn.ulyer.orm.tag;

import cn.ulyer.orm.mapper.MapperMethod;

import java.util.Map;

public class IFTagResolver implements TagResolver{
    @Override
    public String resolverDynamicTag(MapperMethod mapperMethod, Map<String, Object> params) {
        return null;
    }
}
