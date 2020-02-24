package com.keep.keep_backfront.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.keep.keep_backfront.controller.back"))
                .paths(PathSelectors.any())
                .build()
                .groupName("后台管理接口");
    }

    @Bean
    public Docket docketTintin(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.keep.keep_backfront.controller.front"))
                .paths(PathSelectors.any())
                .build()
                .groupName("微信小程序接口");
    }

}
