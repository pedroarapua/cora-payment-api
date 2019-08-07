package com.organization.payment.config;

import io.swagger.annotations.Api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
  
  @Value("${application.name}")
  private String applicationName;
  @Value("${application.description}")
  private String applicationDescription;
  
  @Bean
  public Docket api() { 
    return new Docket(DocumentationType.SWAGGER_2)          
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());                                          
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfo(
      this.applicationName, 
      this.applicationDescription, 
      this.applicationName, 
      "Terms of service", 
      new Contact("Pedro Henrique F. Dias", "www.example.com", "pedroarapua@gmail.com"), 
      "License of API", "API license URL", Collections.emptyList());
  }
}
