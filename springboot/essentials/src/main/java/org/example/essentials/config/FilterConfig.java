package org.example.essentials.config;

import jakarta.servlet.Filter;
import org.example.essentials.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 필터 등록 방식 - FilterRegistrationBean으로 수동 등록
// @Component와 달리 "적용할 url 패턴" 과 "실행 순서"를 직접 지정할 수 있다.
// 필터가 여러 개일 떄 순서를 제어하거나, 특정 경로에만 적용하고 싶을 때 사용한다.


@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter()); // 등록할 필터 인스턴스
        registrationBean.addUrlPatterns("/api/*"); // 이 필터를 적용할 경로
        registrationBean.setOrder(1); // 실행 순서

        return registrationBean;
    }
}
