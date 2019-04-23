package com.asiainfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.ConfigLabel;
import com.asiainfo.entity.Label;
import com.asiainfo.label.service.ConfigLabelService;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.sso.service.SessionManager;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.RedisKey;
import com.asiainfo.util.WebResult;
import com.asiainfo.util.service.JdbcService;
import com.asiainfo.util.service.impl.JdbcServiceImpl;
import com.asiainfo.vo.SysLabelVo;

@RestController
public class LabelController {

	@Resource
	private LabelService labelService;
	@Resource
	private ConfigLabelService configLabelService;
	@Resource
	private SessionManager sessionManager;
	private JdbcService jdbcService=new JdbcServiceImpl();
	
	
	
	@RequestMapping("/getLabelCache")
	public String getLabelCache(String token,String isClear){
		if(isClear.equals("1")){
			
			sessionManager.clearCacheByKey(RedisKey.LABEL_KEY_PREFIX+token);
		}
		
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
	
	@RequestMapping("/saveLabel")
	public String saveLabel(Label label){
		
		
		try {
			this.labelService.saveLabel(label);
		} catch (Exception e) {
			
		}
		
		return WebResult.LABEL_SAVE_SUCCESS;
	}
	
	@RequestMapping("/removeLabelByAccountAndKey")
	public String removeLabelByAccountAndKey(Label label){
		
		
		try {
			this.labelService.removeLabelByAccountAndKey(label);
		} catch (Exception e) {
			
		}
		
		return WebResult.LABEL_REMOVE_SUCCESS;
	}
	
	
	@RequestMapping("/modifyLabelValue")
	public String modifyLabelValue(Label label){
		
		
		try {
			this.labelService.modifyLabelValue(label);
		} catch (Exception e) {
			
		}
		
		return WebResult.LABEL_MODIFY_SUCCESS;
	}
	
	
	@RequestMapping("/queryConfigLabel")
	public String queryConfigLabel(String account){
		ConfigLabel configLabel = this.configLabelService.queryConfigLabel(account);
		
		return JsonUtil.objectToJsonString(configLabel);
	}
	
	@RequestMapping("/modifyConfigLabel")
	public String modifyConfigLabel(String account,String configKey,String configValue,String javaType){
		try {
			String res = jdbcService.modifyConfigLabel(account, configKey, configValue,javaType);
		} catch (Exception e) {
			return WebResult.CONFIG_LABEL_MODIFY_FAIL;
		}
		
		return WebResult.BLANK;
	}

	
	
}
