package cn.course.system.config;

import cn.course.system.interceptor.CheckTokenInterceptor;
import cn.course.system.interceptor.CheckWXTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Autowired
    private CheckWXTokenInterceptor checkWXTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
//                继续添加。。。
                .addPathPatterns("/pages/**")
                .addPathPatterns("/wx/news/**")
                .excludePathPatterns("/pages/**.html")
                .excludePathPatterns("/wx/news/getAll")
                .excludePathPatterns("/login.html");

        registry.addInterceptor(checkWXTokenInterceptor)
                .addPathPatterns("/student/scourse");
    }
}
