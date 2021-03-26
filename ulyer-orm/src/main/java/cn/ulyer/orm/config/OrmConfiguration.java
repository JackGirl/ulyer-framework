package cn.ulyer.orm.config;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.plugin.PluginInvocationHandler;
import cn.ulyer.orm.plugin.handler.ParameterHandler;
import cn.ulyer.orm.plugin.handler.StatementHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class OrmConfiguration {


    private String[] mapperLocations;

    private String[] basePackages;

    private boolean mapperUnderScore = false;

    public Set<String> typeHandlerClasses;

    private Set<String> pluginClasses;

    private List<OrmInterceptor> ormInterceptors;

    public void setBasePackages(String... packages) {
        this.basePackages = packages;
    }

    public void setMapperLocations(String... mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    private final PluginConfiguration pluginConfiguration = new PluginConfiguration();

    public Object newExecutor(Executor target) {
        return pluginConfiguration.proxyByType(PluginType.PREPARE, target);
    }

    public Object newStatementHandler(StatementHandler target) {
        return pluginConfiguration.proxyByType(PluginType.PREPARE, target);
    }

    public Object newParameterHandler(ParameterHandler target) {
        return pluginConfiguration.proxyByType(PluginType.PARAMETER, target);
    }

    public Object newResultHandler(ParameterHandler target) {

        return pluginConfiguration.proxyByType(PluginType.RESULT, target);
    }


    class PluginConfiguration {

        private List<OrmInterceptor> interceptors = new ArrayList<>();

        private boolean init = false;

        List<OrmInterceptor> load() {
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

            return interceptors;
        }

        Object proxyByType(PluginType pluginType, Object target) {
            this.load();
            for (OrmInterceptor interceptor : interceptors) {
                if (pluginType.equals(interceptor.getType())) {
                    target = PluginInvocationHandler.newInstance(target, interceptor);
                }
            }
            return target;
        }


    }

}


