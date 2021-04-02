package cn.ulyer.orm.config;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.handler.*;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.plugin.PluginInvocationHandler;
import cn.ulyer.orm.tag.TagResolver;
import lombok.Data;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Data
public class OrmConfiguration {

    private List<OrmInterceptor> interceptors = new ArrayList<>();

    public final static String PARAM_SPLIT = ".";

    public final static String PARAM_REGEX = "#\\{(.+?)\\}";

    public final static Pattern PARAM_REGEX_PATTERN = Pattern.compile(PARAM_REGEX);

    private String[] mapperLocations;

    private String[] basePackages;

    private boolean mapperUnderScore = false;

    public Set<String> typeHandlerClasses;

    private Set<String> pluginClasses;

    private final RegisterConf registerConf = new RegisterConf();

    public OrmConfiguration(){
    }


    public void setBasePackages(String... packages) {
        this.basePackages = packages;
    }

    public void setMapperLocations(String... mapperLocations) {
        this.mapperLocations = mapperLocations;
    }


    public ExecutorHandler newExecutorHandler(DefaultExecutorHandler target) {
        return (ExecutorHandler) proxyPluginByType(PluginType.EXECUTE, target);
    }

    public StatementHandler newStatementHandler(StatementHandler target) {
        return (StatementHandler) proxyPluginByType(PluginType.PREPARE, target);
    }

    public ParameterHandler newParameterHandler(ParameterHandler target) {
        return (ParameterHandler) proxyPluginByType(PluginType.PARAMETER, target);
    }

    public TypeHandler newResultHandler(TypeHandler target) {
        return (TypeHandler) proxyPluginByType(PluginType.RESULT, target);
    }

    public TypeHandler getTypeHandler(Class handlerClass) {
        return registerConf.typeHandlerMap.get(handlerClass);
    }

    public TypeHandler getTypeHandler(JDBCType jdbcType) {
        return registerConf.jdbcTypeTypeHandlers.get(jdbcType);
    }

    Object proxyPluginByType(PluginType pluginType, Object target) {
        for (OrmInterceptor interceptor : interceptors) {
            if (pluginType.equals(interceptor.getType())) {
                target = PluginInvocationHandler.newInstance(target, interceptor);
            }
        }
        return target;
    }

    public TagResolver getTagResolver(String tagName){
        return registerConf.getTagResolver(tagName);
    }

    public void registerPlugins() {
        for (String pluginClass : pluginClasses) {
            try {
                Class<?> c = Class.forName(pluginClass);
                interceptors.add((OrmInterceptor) c.newInstance());
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("plugin class not loaded :" + pluginClass);

            }
        }

    }

}


