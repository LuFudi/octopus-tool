package com.octopus.filter;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
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
        String traceId = RandomUtil.randomString(20);
        MDC.put(TRACE_ID,traceId);
        //将TRACE_ID加入到响应头
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.addHeader(TRACE_ID, traceId);

        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.remove(TRACE_ID);
        }
    }
}
