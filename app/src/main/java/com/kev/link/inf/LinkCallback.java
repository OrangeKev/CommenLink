package com.kev.link.inf;

import java.util.List;

/**
 * @param <T> 实体类
 */
public interface LinkCallback<T> {

    /**
     * @return 转换的实体类类型
     */
    Class<T> getClassType();
    /**
     * 获取展示的名字，如：城市名称
     */
    String getName(T entity);

    /**
     * 获取实体类中的集合
     */
    List<T> getList(T entity);

    /**
     * @param entity 返回选择的数据
     */
    void onSelect(T entity);


}
