package com.neo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    
    @Bean
    public Docket createRestApi(){
    
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        getParameter()
                 )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful API")
                .description("项目web端接口文档")
                .contact( "Nicky")
                .version("1.0.0")
                .build();
    }
    
    private List<Parameter> getParameter(){
        List<Parameter> result=new ArrayList<Parameter>();
        //构建头部参数
        Parameter p=new ParameterBuilder()
        .name("X-token")
        .description("Description of header")
        .description("登陆的Token")
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(true)
        .build();
        
        result.add(p);
        return result;
    }
    
}
