package com.yilin.www.spingboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * Created by pan yilin on 2016/1/14.
 */
/*@Configuration
@EnableSwagger
@PropertySource("classpath:application.properties")*/
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Value("${swagger.prefix}")
    private String prefix;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
        logger.info("spring swagger config loaded");
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
    	logger.info("SwaggerSpringMvcPlugin loaded");
    	//this.springSwaggerConfig.defaultSwaggerPathProvider().setApiResourcePrefix(prefix);
    	String basepath = this.springSwaggerConfig.defaultSwaggerPathProvider().getApplicationBasePath();
    	logger.info("swagger base path : " + basepath);
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo());
                //.includePatterns(".*?");
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "My Apps API Title",
                "My Apps API Description",
                "My Apps API terms of service",
                "My Apps API Contact Email",
                "My Apps API Licence Type",
                "My Apps API License URL");
        return apiInfo;
    }
   
}