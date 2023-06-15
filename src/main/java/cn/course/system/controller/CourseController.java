package cn.course.system.controller;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Course;
import cn.course.system.domain.Scourse;
import cn.course.system.service.CourseService;
import cn.course.system.service.ScourseService;
import cn.course.system.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private ScourseService scourseService;

    @GetMapping("/wx/getCourse")
    public ResultVO getCourse(String queryStr){
        List<Course> courseList = courseService.getCourse(queryStr);

        return new ResultVO(ResStatus.OK, "success!", courseList);
    }

    @GetMapping("/wx/getCount")
    public ResultVO getCount() {
        List<Scourse> countList = scourseService.getCount();
        Map<String, Integer> map = new HashMap<>();
        for (Scourse scourse : countList) {
            map.put(scourse.getCid(), scourse.getNum());
        }
        return new ResultVO(ResStatus.OK, "success!", map);
    }

    @PostMapping("/findPage")
    public ResultVO getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = courseService.findByPage(queryPageBean);
        return new ResultVO(ResStatus.OK, "success!",page);
    }

    @PutMapping("/")
    @Transactional
    public ResultVO addCourse(@RequestBody Course course) {
        try {
            Course cdata = new Course();
            cdata.setCid(course.getCid());
            cdata.setName(course.getName());
            cdata.setTime(course.getTime());
            cdata.setPlace(course.getPlace());
            cdata.setSum(course.getSum());
            cdata.setType(course.getType());
            boolean result1 = courseService.save(cdata);
            boolean result2 = teacherService.saveByID(course.getTid(), course.getCid());
            if (result1 && result2) {
                return new ResultVO(ResStatus.OK, "success!");
            }

        } catch (Exception e) {
            return new ResultVO(ResStatus.NO, "添加失败，请检查课程ID是否重复");
        }
        return new ResultVO(ResStatus.NO, "添加失败！");
    }

    @PostMapping("/")
    @Transactional
    public ResultVO editCourse(@RequestBody Course course){
        try {
            Course cdata = new Course();
            cdata.setCid(course.getCid());
            cdata.setName(course.getName());
            cdata.setTime(course.getTime());
            cdata.setPlace(course.getPlace());
            cdata.setSum(course.getSum());
            cdata.setType(course.getType());
            UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
            wrapper.eq("cid", course.getCid());
            boolean result1 = courseService.update(course, wrapper);
            boolean result2 = teacherService.updateByID(course.getTid(), course.getCid());
            if (result1 && result2) {
                return new ResultVO(ResStatus.OK, "success!");
            }
        } catch (Exception e) {
        System.out.println("错误信息===》》"+e.getMessage());
        return new ResultVO(ResStatus.NO, "编辑失败，请检查课程ID是否重复");
    }
        return new ResultVO(ResStatus.NO, "编辑失败！");
    }
}
