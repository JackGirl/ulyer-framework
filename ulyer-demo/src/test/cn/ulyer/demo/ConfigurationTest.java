package cn.ulyer.demo;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import org.dom4j.*;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationTest {

    @Test
    public void test() throws FileNotFoundException, DocumentException {
        OrmConfiguration ormConfiguration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        Document document = DocumentHelper.parseText("   \n" +
                "        <if  test=\"\">\n" +
                "            and name = #{name}\n" +
                "            <if test=\"\">\n" +
                "                and id = #{id}\n" +
                "            </if>\n" +
                "            and name = #{name}\n" +
                "        </if>");

         DefaultElement rootElement = (DefaultElement) document .getRootElement();
         Node node = rootElement.selectSingleNode("if");
         node.setText("aaaa");
        System.out.println(rootElement.getStringValue());
    }



}
