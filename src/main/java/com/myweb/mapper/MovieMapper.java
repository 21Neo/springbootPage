package com.myweb.mapper;

import com.myweb.domain.Movie;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface MovieMapper {
    @Select("select id,name,price,action_time from movie")
    @Results({
            @Result(property = "actionTime",column = "action_time")
    })
    List<Movie> find();

    @Select("select id,name,price,action_time actionTime from movie where id=#{id}")
    Movie findById(Integer id);

    @Insert("insert into movie(name,price,action_time) values(#{name},#{price},#{actionTime})")
    int add(Movie movie);

    @Update("update movie set name=#{name},price=#{price},action_time=#{actionTime} where id=#{id}")
    int modify(Movie movie);

    @Delete("delete from movie where id=#{id}")
    int remove(Integer id);

    // 模糊查询
    List<Movie> findByParam(
            @Param("name") String name,
            @Param("beginTime")Date beginTime,
            @Param("endTime")Date endTime
            );
}
