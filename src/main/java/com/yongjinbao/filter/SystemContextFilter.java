package com.yongjinbao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yongjinbao.mybatis.entity.SystemContext;

public class SystemContextFilter implements Filter{
	private Integer pageSize;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Integer offset = 0;
		Integer pageNumber = 1;
		try {
			offset = Integer.parseInt(req.getParameter("pager.offset"));
		} catch (NumberFormatException e) {}
		try {
			pageSize = Integer.parseInt(req.getParameter("pageSize"));
		} catch (NumberFormatException e) {
		}
		try {
			pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
		} catch (NumberFormatException e) {
		}
		try {
			SystemContext.setOrder(req.getParameter("order"));
			SystemContext.setSort(req.getParameter("sort"));
			SystemContext.setPageOffset(offset);
			SystemContext.setPageNumber(pageNumber);
			SystemContext.setPageSize(pageSize);
			SystemContext.setRealPath(((HttpServletRequest)req).getSession().getServletContext().getRealPath("/"));
			chain.doFilter(req,resp);
		} finally {
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageOffset();
			SystemContext.removePageNumber();
			SystemContext.removePageSize();
			SystemContext.removeRealPath();
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 10;
		}
	}

}
