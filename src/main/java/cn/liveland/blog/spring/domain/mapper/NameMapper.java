package cn.liveland.blog.spring.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiyatu
 * @date 2019/7/16 15:02
 * @description
 */
@Mapper
public interface NameMapper {

    @Insert("insert into name(name) values(#{name})")
    Integer saveName(@Param("name") String name);
}
