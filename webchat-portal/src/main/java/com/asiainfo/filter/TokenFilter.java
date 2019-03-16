package com.asiainfo.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.asiainfo.sso.service.RedisSessionService;

@WebFilter(value={"/menu.jsp"})
public class TokenFilter implements Filter {

	@Resource
	private RedisSessionService redisSessionService;
	
	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String token = req.getParameter("token");
		//判断该token是否是有效的账号
		if(token==null){
			return;
		}
		String regex="^\\d{6,}$";
		boolean b = Pattern.matches(regex, token);
		if(!b){
			return;
		}
		//从redis缓存中查看有无此token,如果过期了，直接return
		String res = this.redisSessionService.isSessionExpire(token);
		if("yes".equals(res)){
			return;
		}
		//查看用户登录状态是否为1 
		int status = this.redisSessionService.queryUserStatus(token);
		if(status==0){
			return;
		}
		chain.doFilter(req, resp);
		
		
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
