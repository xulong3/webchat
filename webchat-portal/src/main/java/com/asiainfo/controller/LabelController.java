package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.label.service.LabelService;

@RestController
public class LabelController {

	@Resource
	private LabelService labelService;
	
	@RequestMapping("/getLabelCache")
	public String getLabelCache(String token){
		
		String labels = this.labelService.queryLabel(token);
		return labels;
	}
	
	
}
