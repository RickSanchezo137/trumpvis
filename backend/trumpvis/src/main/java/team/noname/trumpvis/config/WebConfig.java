package team.noname.trumpvis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean<RewriteFilter> registration = new FilterRegistrationBean<>();
        //注册rewrite过滤器　　
        registration.setFilter(new RewriteFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter(RewriteFilter.REWRITE_TO,"/index.html");
        registration.addInitParameter(RewriteFilter.REWRITE_PATTERNS, "/vis/*");
        registration.setName("rewriteFilter");
        registration.setOrder(1);
        return registration;
    }
}
