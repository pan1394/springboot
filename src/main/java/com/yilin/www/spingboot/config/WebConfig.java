package com.yilin.www.spingboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yilin.www.spingboot.interceptor.LocaleInterceptor;

@Configuration
//@EnableWebMvc  //this annotation would make the @EnableAutoConfiguration infects in @SpringBootApplication
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LocaleInterceptor localeInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor).addPathPatterns("/test/**");
       // registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
       // registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
    }
}