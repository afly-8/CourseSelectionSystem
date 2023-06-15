package cn.course.system.service;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Scourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xiaole
* @description 针对表【scourse】的数据库操作Service
* @createDate 2023-02-11 15:54:47
*/
public interface ScourseService extends IService<Scourse> {
    ResultVO selectCourse(String sid, String cid);

    List<Scourse> getCount();

    PageResult findByPage(QueryPageBean queryPageBean);
}
