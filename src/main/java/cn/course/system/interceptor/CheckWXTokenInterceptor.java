package cn.course.system.interceptor;

import cn.course.system.common.vo.ResStatus;
import cn.course.system.common.vo.ResultVO;
import cn.course.system.domain.Student;
import cn.course.system.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class CheckWXTokenInterceptor implements HandlerInterceptor {

    @Resource
    private StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        String token = request.getHeader("access-token");

        if (token == null) {
            // 提示请登录
            ResultVO resultVO = new ResultVO(ResStatus.NO, "请先登录！", null);
            doResponse(response, resultVO);
        } else {
            try {
                // token校验
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("XiaoLe999");
                // 通过异常捕获来执行
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                Claims body = claimsJws.getBody(); // 获取token中的数据
                String subject = body.getSubject(); // 生成token时设置的subject
                return true;
            } catch (ExpiredJwtException e){
                ResultVO resultVO = new ResultVO(ResStatus.NO, "登录过期！", null);
                doResponse(response, resultVO);
            } catch (UnsupportedJwtException e) {
                ResultVO resultVO = new ResultVO(ResStatus.NO, "token不合法！", null);
                doResponse(response, resultVO);
            } catch (Exception e) {
                System.out.println("错误信息："+e.getMessage());
                ResultVO resultVO = new ResultVO(ResStatus.NO, "请重新登录！", null);
                doResponse(response, resultVO);
            }
        }
        return false;
    }

    private void doResponse(HttpServletResponse response, ResultVO resultVO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVO);
        out.println(s);
        out.flush();
        out.close();
    }

}
