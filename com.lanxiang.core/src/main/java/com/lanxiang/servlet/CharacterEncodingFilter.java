package com.lanxiang.servlet;

import javax.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lanxiang on 2016/11/11.
 */
@Singleton
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    private boolean forceEncoding = true;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (this.forceEncoding || request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(this.encoding);
            response.setCharacterEncoding(this.encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        String force = filterConfig.getInitParameter("forceEncoding");
        this.forceEncoding = (force == null) || Boolean.valueOf(force);
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }
}
