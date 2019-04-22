package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.SysLabel;
import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.RedisKey;
import com.asiainfo.util.WebResult;
import com.asiainfo.vo.SysLabelVo;

@RestController
public class LabelController {

	@Resource
	private LabelService labelService;
	@Resource
	private ConfigLabelService configLabelService;
	@Resource
	private SessionManager sessionManager;
	
	
	
	@RequestMapping("/getLabelCache")
	public String getLabelCache(String token){
		
		String labels = this.labelService.queryLabelCache(token);
		return labels;
	}
	
	@RequestMapping("/getSysLabelCache")
	public String getSysLabelCache(String token,String isClear){
		
		if(isClear.equals("1")){
			
			sessionManager.clearHashCacheByField(RedisKey.SYSLABEL_KEY, RedisKey.SYSLABEL_HASH_KEY_PREFIX+token);
		}
		
		String sysLabels = this.labelService.querySysLabelCache(token);
		return sysLabels;
	}
	
	@RequestMapping("/modifySysLabel")
	public String modifySysLabel(SysLabelVo sysLabelVo){
		
		
		this.labelService.modifySysLabel(sysLabelVo);
		
		return WebResult.SYS_LABEL_SAVE_SUCCESS;
	}

	
	
}
