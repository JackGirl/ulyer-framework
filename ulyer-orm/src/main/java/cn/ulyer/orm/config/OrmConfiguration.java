package cn.ulyer.orm.config;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.mapper.handler.*;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.plugin.PluginInvocationHandler;
import lombok.Data;

import java.util.*;

@Data
public class OrmConfiguration {

    private final static String PARAM_SPLIT = ".";

    final static String PARAM_REGEX = "#\\{(.+?)\\}";

    private String[] mapperLocations;

    private String[] basePackages;

    private boolean mapperUnderScore = false;

    public Set<String> typeHandlerClasses;

    private Set<String> pluginClasses;


    private final RegisterConf registerConf = new RegisterConf();

    public static String paramSplit(){
        return PARAM_SPLIT;
    }

    public static String paramRegex(){
        return PARAM_REGEX;
    }

    public void setBasePackages(String... packages) {
        this.basePackages = packages;
    }

    public void setMapperLocations(String... mapperLocations) {
        this.mapperLocations = mapperLocations;
    }



    public Executor newExecutor(Executor target) {
        return (Executor) registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public StatementHandler newStatementHandler(StatementHandler target) {
        return (StatementHandler) registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public ParameterHandler newParameterHandler(ParameterHandler target) {
        return (ParameterHandler) registerConf.proxyPluginByType(PluginType.PARAMETER, target);
    }

    public TypeHandler newResultHandler(TypeHandler target) {
        return (TypeHandler) registerConf.proxyPluginByType(PluginType.RESULT, target);
    }


    @Data
     class RegisterConf {

        private List<OrmInterceptor> interceptors = new ArrayList<>();

        Map<Class<?>, TypeHandler> typeHandlerMap = new HashMap<>();

        private boolean init = false;

        public List<OrmInterceptor> getInterceptors(){
            if(!this.init){
                this.registerPlugins();
            }
            return interceptors;
        }

        public RegisterConf(){
            this.registerTypeHandlers();
        }

        private void registerTypeHandlers() {
            //typeHandlerMap.put()
            typeHandlerMap.put(String.class,new StringTypeHandler());
            typeHandlerMap.put(Integer.class,new IntegerTypeHandler());
            typeHandlerMap.put(int.class,new IntegerTypeHandler());
            typeHandlerMap.put(List.class,new ListTypeHandler());
        }

        void registerPlugins() {
            if (!init) {
                synchronized (this) {
                    if(!init){
                        for (String pluginClass : pluginClasses) {
                            try {
                                Class<?> c = Class.forName(pluginClass);
                                interceptors.add((OrmInterceptor) c.newInstance());
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException("plugin class not loaded :" + pluginClass);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                        this.init = true;
                    }
                }
            }
        }

        Object proxyPluginByType(PluginType pluginType, Object target) {
            this.registerPlugins();
            for (OrmInterceptor interceptor : interceptors) {
                if (pluginType.equals(interceptor.getType())) {
                    target = PluginInvocationHandler.newInstance(target, interceptor);
                }
            }
            return target;
        }


    }

}


