package cn.ulyer.orm.plugin;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private Integer page;

    private Integer pageSize;

    private long total;

    private List<T> records;


}
