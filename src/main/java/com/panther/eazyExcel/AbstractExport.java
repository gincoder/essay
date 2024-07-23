package com.panther.eazyExcel;

import com.alibaba.excel.ExcelWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: AbstractExport.java, 2024/7/5 10:57 $
 */
public abstract class AbstractExport<T,K> {

    public abstract void export(User user) throws InterruptedException ;


    /**
     * 导出
     *
     * @param response 输出流
     * @param pageSize 每页大小
     * @param t        导出条件
     * @param k        Excel内容实体类
     * @param fileName 文件名称
     */
    public void export(HttpServletResponse response, int pageSize, T t, Class<K> k, String fileName) throws Exception {
        ExcelWriter writer = null;

    }

    /**
     * 查询导出总条数
     *
     * @param t
     * @return
     */
    public abstract int countExport(T t);

}
