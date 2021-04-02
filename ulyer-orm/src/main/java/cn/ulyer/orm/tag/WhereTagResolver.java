package cn.ulyer.orm.tag;

import cn.hutool.core.util.StrUtil;
import cn.ulyer.orm.mapper.MapperWrapper;
import org.dom4j.Element;

public class WhereTagResolver implements TagResolver{


    @Override
    public void resolverDynamicTag(MapperWrapper mapperWrapper, Element element) {
        String whereText = element.getStringValue();
        String preStr = whereText.substring(0,whereText.indexOf("and"));
        if(StrUtil.isBlank(preStr)){
            whereText = whereText.replaceFirst("and","");
        }
        element.setContent(null);
        element.setText(" where "+whereText);
    }




}
