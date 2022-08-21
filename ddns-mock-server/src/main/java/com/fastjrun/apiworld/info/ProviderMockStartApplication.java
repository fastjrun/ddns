/*
 * Copyright (C) 2021 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.apiworld.info;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, ValidationAutoConfiguration.class}, scanBasePackages = {"com.fastjrun.mock"})
public class ProviderMockStartApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderMockStartApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
        System.out.println(ProviderMockStartApplication.class.getResource("/"));
    }

    @Bean
    public Docket customDocket() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.fastjrun.mock.web.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("快嘉").description("后台脚手架").version("1.0").build();
    }
}
