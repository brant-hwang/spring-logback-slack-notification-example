package io.brant.spring.logback;

import io.brant.spring.logback.filters.LogbackMdcFilter;
import io.brant.spring.logback.filters.MultiReadableHttpServletRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class SpringLogbackMdcExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLogbackMdcExampleApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean multiReadableHttpServletRequestFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		MultiReadableHttpServletRequestFilter multiReadableHttpServletRequestFilter = new MultiReadableHttpServletRequestFilter();
		registrationBean.setFilter(multiReadableHttpServletRequestFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean logbackMdcFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		LogbackMdcFilter logbackMdcFilter = new LogbackMdcFilter();
		registrationBean.setFilter(logbackMdcFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errors/401");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errors/404");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/500");
			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}
}
