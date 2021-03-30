package cn.ulyer.orm.plugin;

public interface PageDialect {

    String getPageSql(IPage  page,String originSql);

}
