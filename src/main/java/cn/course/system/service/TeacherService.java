package cn.course.system.service;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaole
* @description 针对表【teacher】的数据库操作Service
* @createDate 2023-02-07 21:49:33
*/
public interface TeacherService extends IService<Teacher> {

    PageResult findByPage(QueryPageBean queryPageBean);

    boolean saveByID(String tid, String cid);

    boolean updateByID(String tid, String cid);
}
