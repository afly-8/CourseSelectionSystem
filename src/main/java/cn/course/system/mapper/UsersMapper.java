package cn.course.system.mapper;

import cn.course.system.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xiaole
* @description 针对表【users(用户 )】的数据库操作Mapper
* @createDate 2022-10-22 10:56:29
* @Entity cn.cooxl.mall.domain.Users
*/

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}




