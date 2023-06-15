package cn.course.system.controller;

import cn.course.system.common.vo.*;
import cn.course.system.domain.Scourse;
import cn.course.system.service.ScourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/scourse")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class ScourseController {

    @Resource
    private ScourseService scourseService;

    @PostMapping("/findPage")
    public ResultVO getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = scourseService.findByPage(queryPageBean);
        return new ResultVO(ResStatus.OK, "success!",page);
    }

    @PostMapping("/")
    public ResultVO editScourse(@RequestBody ScourseVO scourseVO){
        Scourse scourse = new Scourse();
        scourse.setScid(scourseVO.getScid());
        scourse.setSid(scourseVO.getSid());
        scourse.setCid(scourseVO.getCid());

        boolean update = scourseService.updateById(scourse);
        if (update) {
            return new ResultVO(ResStatus.OK, "更新成功", null);
        } else {
            return new ResultVO(ResStatus.NO, "更新失败", null);
        }
    }
}
