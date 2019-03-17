package com.asiainfo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.asiainfo.sso.service.RedisSessionService;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.PageTemplate;

@WebFilter(value={"/menu.jsp"})
public class TokenFilter implements Filter {

	//需要手动注入
	private RedisSessionService redisSessionService;
	
	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		String token = req.getParameter("token");
		//判断该token是否是有效的账号
		if(token==null){
			LoggerUtil.info(this.getClass(), "----------token为空");
			
			out.append(PageTemplate.getAccessForbiddenPage("没有登录令牌"));
			return;
		}
		String regex="^\\d{6,}$";
		boolean b = Pattern.matches(regex, token);
		if(!b){
			LoggerUtil.info(this.getClass(), "----------token无效");
			out.append(PageTemplate.getAccessForbiddenPage("无效的登录令牌"));
			return;
		}
		//从redis缓存中查看有无此token,如果过期了，直接return
		String res = this.redisSessionService.isSessionExpire(token);
		if("yes".equals(res)){
			LoggerUtil.info(this.getClass(), "----------会话过期");
			out.append(PageTemplate.getAccessForbiddenPage("会话已过期，请重新登录"));
			return;
		}
		//查看用户登录状态是否为1 
		int status = this.redisSessionService.queryUserStatus(token);
		if(status==0){
			LoggerUtil.info(this.getClass(), "----------当前为离线状态");
			out.append(PageTemplate.getAccessForbiddenPage("用户当前为离线状态，请重新登录"));
			return;
		}
		chain.doFilter(req, resp);
		
		
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		ApplicationContext ac =  WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());  
		redisSessionService = (RedisSessionService) ac.getBean("redisSessionService");
	}

}
