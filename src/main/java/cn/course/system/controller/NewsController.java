package cn.course.system.controller;

import cn.course.system.common.vo.PageResult;
import cn.course.system.common.vo.QueryPageBean;
import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.News;
import cn.course.system.service.NewsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/wx/news")
// 允许跨域
@CrossOrigin(origins = "*")
@ResponseBody
public class NewsController {

    @Resource
    private NewsService newsService;

    @GetMapping("/getAll")
    public ResultVO getAll(){
        List<News> newsList = newsService.getAll();
        return new ResultVO(ResStatus.OK, "success!",newsList);
    }

    @PostMapping("/findPage")
    public ResultVO getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = newsService.findByPage(queryPageBean);
        return new ResultVO(ResStatus.OK, "success!",page);
    }

    @PutMapping("/")
    @Transactional
    public ResultVO addNews(@RequestBody News news) {
        try {
            boolean result = newsService.save(news);
            if (result) {
                return new ResultVO(ResStatus.OK, "success!");
            }
        } catch (Exception e) {
            return new ResultVO(ResStatus.NO, "添加失败，请检查日期是否填写正确！");
        }
        return new ResultVO(ResStatus.NO, "添加失败！");
    }

    @PostMapping("/editNews")
    public ResultVO editNews(@RequestBody News news) {
        try {
            boolean result = newsService.update(news);
            if (!result) {
                return new ResultVO(ResStatus.NO, "更新失败！");
            }
        } catch (Exception e) {
            return new ResultVO(ResStatus.NO, "更新失败，请检查日期是否填写正确！");
        }
        return new ResultVO(ResStatus.OK, "更新成功！");
    }

    @DeleteMapping("/{id}")
    public ResultVO deleteNews(@PathVariable String id) {
        boolean result = newsService.remove(id);
        if (!result) {
            return new ResultVO(ResStatus.NO, "删除失败！");
        }
        return new ResultVO(ResStatus.OK, "删除成功！");
    }
}
