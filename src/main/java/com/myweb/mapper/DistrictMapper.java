package com.myweb.mapper;

import com.myweb.domain.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {
    /**
     * 查询分页数据
     *
     * @param name 模糊查询关键字
     * @param page 页数
     * @param size 每页条数
     * @return 分页数据
     */
    List<District> find(@Param("name") String name,
                        @Param("page") Integer page,
                        @Param("size") Integer size);

    List<District> findByParam(@Param("name") String name);

    /**
     * 查询符合条件的总记录数
     * @param name 模糊查询关键字
     * @return 总记录数
     */
    Long getTotal(@Param("name") String name);
}
