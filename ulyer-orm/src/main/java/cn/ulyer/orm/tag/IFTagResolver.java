package cn.ulyer.orm.tag;


import cn.ulyer.orm.mapper.MapperWrapper;
import org.dom4j.Element;


public class IFTagResolver implements TagResolver{



    @Override
    public void resolverDynamicTag(MapperWrapper mapperWrapper, Element element) {
        String test  = element.attributeValue("test");
        String content  = element.getStringValue();
        element.setContent(null);
        element.setText(content);
    }


}
