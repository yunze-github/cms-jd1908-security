package com.briup.apps.cms.config;

import com.briup.apps.cms.web.Interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@CrossOrigin//标注在类或者方法上面，处理跨域cors请求
public class WebConfig implements WebMvcConfigurer {

	/**
	 * 将自定义拦截器作为Bean写入配置
	 * @return
	 */
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截路径可自行配置多个 可用 ，分隔开
		registry.addInterceptor(jwtInterceptor())
//				.addPathPatterns("/category/**","/article/**","/user/**","/role/**","/privilege/**")
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/swagger-resources/**","/v2/**","/swagger-ui.html","/webjars/**",
						"/user/login","/user/logout");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","PUT","OPTIONS","DELETE","PATCH")
				.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
						"Access-Control-Request-Headers")
				.allowCredentials(true)
				.maxAge(3600);
	}



}
