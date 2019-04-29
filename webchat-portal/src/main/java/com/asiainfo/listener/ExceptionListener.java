package com.asiainfo.listener;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.asiainfo.common.service.BaseExceptionService;
import com.asiainfo.entity.UnknownException;
import com.asiainfo.util.ExceptionUtil;

@Component
public class ExceptionListener extends HandlerExceptionResolverComposite{
	
	@Resource
	private BaseExceptionService baseExceptionService; 
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) {
		
		try {
			baseExceptionService.saveBaseException(new UnknownException("未知用户",ExceptionUtil.getExceptionMessage(exception)));
		} catch (Exception e) {
			
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("exception");
		mv.addObject("msg", exception.getMessage());

		
		return mv;
	}
	
	public static void main(String[] args) {
		File f = new File("E:\\gdesign\\file-server-root");
		File[] files = f.listFiles();
		String aa="";
		
		for (File file : files) {
			if(file.isDirectory()){
				aa+=file.getName()+"\r\n";
			}
		}
		
		System.out.println(aa);
		
	}
}
