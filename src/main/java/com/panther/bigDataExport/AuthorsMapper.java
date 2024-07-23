package com.panther.bigDataExport;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: AuthorsMapper
 * @author: Kevin
 * @date: 2023/3/19
 **/
@Mapper
public interface AuthorsMapper {
    List<Authors> selectByExample();

    List<Authors> streamByExample(); //以stream形式从mysql获取数据

}
