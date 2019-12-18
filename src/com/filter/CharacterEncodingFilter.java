package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * �������� HTTP�����ַ�����Ĺ�������ͨ������������encodingָ��ʹ�ú����ַ�����
 */
@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "utf-8")
        })
public class CharacterEncodingFilter implements Filter {
    protected String encoding;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (encoding != null) {
            /*���������ĵ��ַ�����*/
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        /*��ȡ��ʼ������*/
        this.encoding = fConfig.getInitParameter("encoding");
	}
}

