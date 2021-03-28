package cn.ulyer.orm.config;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.mapper.MapperDefinition;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.plugin.PluginInvocationHandler;
import cn.ulyer.orm.plugin.handler.ParameterHandler;
import cn.ulyer.orm.plugin.handler.StatementHandler;
import cn.ulyer.orm.result.ResultTypeHandler;
import lombok.Data;

import java.util.*;

@Data
public class OrmConfiguration {


    private String[] mapperLocations;

    private String[] basePackages;

    private boolean mapperUnderScore = false;

    public Set<String> typeHandlerClasses;

    private Set<String> pluginClasses;


    private final RegisterConf registerConf = new RegisterConf();


    public void setBasePackages(String... packages) {
        this.basePackages = packages;
    }

    public void setMapperLocations(String... mapperLocations) {
        this.mapperLocations = mapperLocations;
    }



    public Object newExecutor(Executor target) {
        return registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public Object newStatementHandler(StatementHandler target) {
        return registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public Object newParameterHandler(ParameterHandler target) {
        return registerConf.proxyPluginByType(PluginType.PARAMETER, target);
    }

    public Object newResultHandler(ParameterHandler target) {
        return registerConf.proxyPluginByType(PluginType.RESULT, target);
    }


    class RegisterConf {

        private List<OrmInterceptor> interceptors = new ArrayList<>();

        Map<Class<?>, ResultTypeHandler> typeHandlerMap = new HashMap<>();

        private boolean init = false;

        public List<OrmInterceptor> getInterceptors(){
            if(!this.init){
                this.registerPlugins();
            }
            return interceptors;
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


