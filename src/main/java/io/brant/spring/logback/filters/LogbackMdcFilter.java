package io.brant.spring.logback.filters;

import io.brant.spring.logback.util.AgentUtils;
import io.brant.spring.logback.util.HttpUtils;
import io.brant.spring.logback.util.MDCUtil;
import io.brant.spring.logback.util.RequestWrapper;
import org.apache.log4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogbackMdcFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		RequestWrapper requestWrapper = RequestWrapper.of(request);

		// Set Http Header
		MDCUtil.setJsonValue(MDCUtil.HEADER_MAP_MDC, requestWrapper.headerMap());

		// Set Http Body
		MDCUtil.setJsonValue(MDCUtil.PARAMETER_MAP_MDC, requestWrapper.parameterMap());

		// If you use SpringSecurity, you could SpringSecurity UserDetail Information.
		MDCUtil.setJsonValue(MDCUtil.USER_INFO_MDC, HttpUtils.getCurrentUser());

		// Set Agent Detail
		MDCUtil.setJsonValue(MDCUtil.AGENT_DETAIL_MDC, AgentUtils.getAgentDetail((HttpServletRequest) request));

		// Set Http Request URI
		MDCUtil.set(MDCUtil.REQUEST_URI_MDC, requestWrapper.getRequestUri());

		try {
			chain.doFilter(request, response);
		} finally {
			MDC.clear();
		}
	}

	@Override
	public void destroy() {

	}
}
