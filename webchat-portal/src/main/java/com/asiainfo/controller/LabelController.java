package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.label.service.LabelService;

@RestController
public class LabelController {

	@Resource
	private LabelService labelService;
	@Resource
	private ConfigLabelService configLabelService;
	
	
	
	@RequestMapping("/getLabelCache")
	public String getLabelCache(String token){
		
		String labels = this.labelService.queryLabelCache(token);
		return labels;
	}
	
	@RequestMapping("/getSysLabelCache")
	public String getSysLabelCache(String token){
		
		String sysLabels = this.labelService.querySysLabelCache(token);
		return sysLabels;
	}
	
	

	
	
}
