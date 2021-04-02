package cn.ulyer.orm.tag;

import cn.ulyer.orm.mapper.MapperWrapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

public class DefaultTagResolver {

    public String resolverDynamicTag(MapperWrapper mapperWrapper) throws DocumentException {
        String tag =mapperWrapper.getBoundSql();
        Document dom = DocumentHelper.parseText(tag);
        Element ROOT = dom.getRootElement();
        Iterator<Element> elementIterator = ROOT.elementIterator();
        while (elementIterator.hasNext()){
            Element child = elementIterator.next();
            doResolverDepp(mapperWrapper,child);
        }
        return ROOT.getStringValue();
    }

    private void doResolverDepp(MapperWrapper mapperWrapper,Element element) throws DocumentException {
        Iterator<Element> elementIterator = element.elementIterator();
        if(elementIterator.hasNext()){
            while(elementIterator.hasNext()){
                Element deepChild = elementIterator.next();
                doResolverDepp(mapperWrapper,deepChild);
                resolverText(element,mapperWrapper);
            }
        }else{
            resolverText(element,mapperWrapper);
        }
    }


    private void resolverText(Element element,MapperWrapper mapperWrapper) throws DocumentException {
        TagResolver tagResolver = mapperWrapper.getOrmConfiguration().getTagResolver(element.getName());
        tagResolver.resolverDynamicTag(mapperWrapper,element);
    }






}
