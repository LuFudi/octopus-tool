package com.octopus.filter;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 为每一个请求生成traceId
 * @author Administrator
 */
@Component
public class MdcFilter implements Filter {

    public static final String TRACE_ID = "TRACE_ID";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = RandomUtil.randomString(10);
        MDC.put(TRACE_ID,traceId);
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.remove(TRACE_ID);
        }
    }
}
