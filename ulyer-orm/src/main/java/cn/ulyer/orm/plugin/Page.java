package cn.ulyer.orm.plugin;

import lombok.Data;

import java.util.List;

@Data
public class Page <E>implements IPage<E>{

    private Integer page;

    private Integer pageSize;

    private long total;

    private List<E> records;

    private long pages;

    public Page(Integer page,Integer pageSize){
        this.page = page==null? 1 : page <=0? 1:page;
        this.pageSize = pageSize==null? 20 : pageSize < 0? 20:pageSize;
    }

    @Override
    public void setTotal(long total){
        Integer p = Math.toIntExact(total / pageSize);
        Integer s = Math.toIntExact(total % pageSize>0?1:0);
        this.pages = total==0?0:p+s;
        this.page = Math.toIntExact(page>pages?pages:page);
        this.total = total;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public long getPages() {
        return pages;
    }

    @Override
    public void setSize(Integer size) {
        this.pageSize = size;
    }

    @Override
    public void setRecords(List records) {
        this.records = records;
    }
}
