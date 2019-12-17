package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

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

