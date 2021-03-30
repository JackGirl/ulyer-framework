package cn.ulyer.orm.plugin;

import java.util.List;

public interface IPage<E> {

    Integer getPage();

    Integer getPageSize();

    long getPages();

    long getTotal();

    List<E> getRecords();

    void setPage(Integer page);

    void setSize(Integer size);

    void setTotal(long total);

    void setRecords( List<E> records);

}
