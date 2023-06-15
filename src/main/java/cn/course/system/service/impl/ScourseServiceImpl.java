package cn.course.system.service.impl;

import cn.course.system.common.vo.*;
import cn.course.system.domain.Course;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.course.system.domain.Scourse;
import cn.course.system.service.ScourseService;
import cn.course.system.mapper.ScourseMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author xiaole
* @description 针对表【scourse】的数据库操作Service实现
* @createDate 2023-02-11 15:54:47
*/
@Service
public class ScourseServiceImpl extends ServiceImpl<ScourseMapper, Scourse>
    implements ScourseService{

    @Resource
    private ScourseMapper scourseMapper;


    public List<Scourse> getBySid(String sid, String cid) {
        QueryWrapper<Scourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", sid).eq("cid", cid);
        return scourseMapper.selectList(queryWrapper);
    }

    @Override
    public ResultVO selectCourse(String sid, String cid) {
        List<Scourse> selectScourse = getBySid(sid, cid);
        if (selectScourse.size()>0) {
            return new ResultVO(ResStatus.NO, "请勿重复选课！", null);
        } else {
            Scourse scourse = new Scourse();
            String date = new Date().getTime() + "";
            scourse.setScid("sc"+ date.substring(0, 4) + sid + RandomStringUtils.randomAlphabetic(4));
            scourse.setSid(sid);
            scourse.setCid(cid);
            int insert = scourseMapper.insert(scourse);
            if (insert > 0) {
                return new ResultVO(ResStatus.OK, "选课成功！", null);
            } else {
                return new ResultVO(ResStatus.NO, "选课异常！", null);
            }
        }
    }

    @Override
    public List<Scourse> getCount() {
        return scourseMapper.getCount();
    }

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<ScourseVO> page = new Page<>(currentPage,pageSize);
        scourseMapper.findByPage(page, queryString);
        return new PageResult(page.getTotal(), page.getRecords());
    }


}




