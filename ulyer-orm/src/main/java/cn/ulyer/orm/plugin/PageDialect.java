package cn.ulyer.orm.plugin;

public interface PageDialect {

    String getPageSql(Page page,String originSql);

}
