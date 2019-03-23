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

import com.asiainfo.label.service.LabelService;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.PageTemplate;

@WebFilter(value={"/menu.jsp"})
public class TokenAuthFilter implements Filter {

	//需要手动注入
	private SessionManager sessionManager;
	private LabelService labelService;
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
		String res = this.sessionManager.isSessionExpire(token);
		if("yes".equals(res)){
			LoggerUtil.info(this.getClass(), "----------会话过期");
			out.append(PageTemplate.getAccessForbiddenPage("会话已过期，请重新登录"));
			return;
		}
		//查看是否非法登录
		String res1 = this.sessionManager.isIllegalLogin(token);
		if("yes".equals(res1)){
			LoggerUtil.info(this.getClass(), "----------非法登录");
			out.append(PageTemplate.getAccessForbiddenPage("非法登录"));
			return;
		}else if("no".equals(res1)){
			
			LoggerUtil.info(this.getClass(), "----------通过了身份验证过滤器");
			
			//判断有无label和syslabel
			
			String s1 = this.sessionManager.hasSysLabelCache(token);
			String s2 = this.sessionManager.hasLabelCache(token);
			if("no".equals(s1)) {
				String result1 = labelService.loadSysLabelToRedis(token);
			}
			if("no".equals(s2)){
				
				String result2 = labelService.loadLabelToRedis(token);
			}
				
			chain.doFilter(req, resp);
			return;
		}
		
		
		
		
		
		
		
	}
	

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		ApplicationContext ac =  WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());  
		sessionManager = (SessionManager) ac.getBean("sessionManager");
		labelService = (LabelService) ac.getBean("labelService");
	}

}
