package hsf.HSF301_BigAssignment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/admin", "/customer")
                .addPathPatterns("/admin/**", "/customer/**")
                .addPathPatterns("/api/v1/payment", "/api/v1/cart")
                .addPathPatterns("/api/v1/payment/**", "/api/v1/cart/**");
    }
}