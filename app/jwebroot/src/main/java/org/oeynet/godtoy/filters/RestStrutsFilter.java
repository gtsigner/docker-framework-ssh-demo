package org.oeynet.godtoy.filters;


import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器
 * Created by zhaojunlike on 6/1/2017.
 */
public class RestStrutsFilter extends StrutsPrepareAndExecuteFilter {

    private Logger logger = Logger.getLogger(this.getClass());


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();
        System.err.println("NewRequest Url:" + path);
        if (path.contains("/api/")) {
            System.err.println("跳过struts拦截");
            chain.doFilter(request, response);
        } else {
            System.err.println("进入struts拦截");
            super.doFilter(request, response, chain);
        }
    }
}
