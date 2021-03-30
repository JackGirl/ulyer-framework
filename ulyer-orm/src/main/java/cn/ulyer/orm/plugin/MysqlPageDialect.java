package cn.ulyer.orm.plugin;

public class MysqlPageDialect implements PageDialect{

    @Override
    public String getPageSql(IPage page, String originSql) {
        return originSql + " limit "+(page.getPage()-1)+","+page.getPageSize();
    }

}
