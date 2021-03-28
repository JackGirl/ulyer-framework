package cn.ulyer.orm.config;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.mapper.handler.PrepareStatementHandler;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.plugin.PluginInvocationHandler;
import cn.ulyer.orm.mapper.handler.ParameterHandler;
import cn.ulyer.orm.mapper.handler.StatementHandler;
import cn.ulyer.orm.mapper.handler.ResultTypeHandler;
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



    public Executor newExecutor(Executor target) {
        return (Executor) registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public PrepareStatementHandler newStatementHandler(StatementHandler target) {
        return (PrepareStatementHandler) registerConf.proxyPluginByType(PluginType.PREPARE, target);
    }

    public ParameterHandler newParameterHandler(ParameterHandler target) {
        return (ParameterHandler) registerConf.proxyPluginByType(PluginType.PARAMETER, target);
    }

    public ResultTypeHandler newResultHandler(ResultTypeHandler target) {
        return (ResultTypeHandler) registerConf.proxyPluginByType(PluginType.RESULT, target);
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


