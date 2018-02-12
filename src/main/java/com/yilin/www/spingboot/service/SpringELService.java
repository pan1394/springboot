package com.yilin.www.spingboot.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
//导入配置文件，但是使用这个注解需要依赖PropertySourcesPlaceholderConfigurer
@PropertySource("classpath:test.properties")
public class SpringELService {


	public String field = "constant field";
    //普通注入字符串
    @Value("ABC")
    private String normal;

    //通过systemProperties的bean获得系统名称
    @Value("#{systemProperties['os.name']}")
    private String osName;
    
    //get environment variable
    @Value("#{systemEnvironment['NUMBER_OF_PROCESSORS']}")
    private String numOfProcessor;
    
    //获得Spring 容器中其中一个bean的属性
    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    @Value("#{springELService.field}")
    private String serviceUsername;

    //获得本地文件的resource对象
    @Value("classpath:test.properties")
    private Resource testFile;

    //获得配置文件中的配置项
    @Value("${test.propertyA}")
    private String propertyA;

    //获得配置文件中的配置项
    @Value("${test.propertyB}")
    private String propertyB;

    //通过URL获得resource对象
    @Value("http://www.baidu.com")
    private Resource testUrl;

    //这里通过environment获得property
    @Autowired
    private Environment environment;

    //注册一个PropertySourcesPlaceholderConfigurer的Bean
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() throws IOException {
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(this.numOfProcessor);
        System.out.println(randomNumber);
        System.out.println(propertyA);
        System.out.println(serviceUsername);
        System.out.println(IOUtils.toString(testFile.getInputStream()));
        System.out.println(IOUtils.toString(testUrl.getInputStream()));
        System.out.println(environment.getProperty("test.propertyB"));

    }

}