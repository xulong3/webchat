package com.asiainfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asiainfo.common.service.FileService;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.entity.User;
import com.asiainfo.label.service.LabelService;
import com.asiainfo.sso.service.UserService;
import com.asiainfo.util.JsonUtil;
import com.asiainfo.util.LoggerUtil;
import com.asiainfo.util.WebResult;
import com.asiainfo.vo.MessageVo;

@RestController
public class FileController {
	
	@Resource
	private FileService fileService;
	@Resource
	private UserService userService;
	@Resource
	private LabelService labelService;

	@RequestMapping("/uploadUserRootDir")
	public String uploadUserRootDir(String token,String date){
		LoggerUtil.info(this.getClass(), "----------"+token);
		LoggerUtil.info(this.getClass(), "----------"+date);
		
		String res = fileService.createUserRootDir(token, date);
		if("yes".equals(res)){
			return WebResult.INIT_USER_ROOT_SUCCESS;
		}else{
			return WebResult.INIT_USER_ROOT_FAIL;
		}
	}
	
	@RequestMapping("/createP2pChatFile")
	public void createP2pChatFile(String userAccount,String friendAccount){
		
		
		this.fileService.createP2pChatFile(userAccount, friendAccount);
	}
	
	@RequestMapping("/saveMessage")
	public String saveMessage(MessageVo messageVo){
		
		MessageVo msg = this.fileService.saveMessageToFile(messageVo);
		return JsonUtil.objectToJsonString(msg);
	}
	
	@RequestMapping("/getTodayMessage")
	public String getTodayMessage(MessageVo messageVo){
		
		Long startTime=null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getTime();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		List<MessageVo> voList = this.fileService.getMessage(messageVo, startTime, null);
		
		
		return JsonUtil.objectToJsonString(voList);
	}
	@RequestMapping("/getMessageOneDay")
	public String getMessageOneDay(MessageVo messageVo,String day){
		Long startTime=null;
		Long endTime=null;
		try {
			Date date = new SimpleDateFormat("yyy-MM-dd").parse(day);
			startTime=date.getTime();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			
			endTime=c.getTime().getTime();
			
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		List<MessageVo> voList = this.fileService.getMessage(messageVo, startTime, endTime);
		
		return JsonUtil.objectToJsonString(voList);
	}
	
	@RequestMapping("/getMessageDays")
	public String getMessageDays(MessageVo messageVo){
		List<String> list = this.fileService.getMessageDays(messageVo);
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s).append(",");
		}
		return sb.substring(0, sb.length()-1).toString();
	}
	
	@RequestMapping("/loadHistoryMessageTree")
	public String loadHistoryMessageTree(MessageVo messageVo){
		List<Map<String, Object>> list = this.fileService.getMessageGroupTree(messageVo);
		System.err.println(list.size());
		return JsonUtil.listToJsonString(list);
		
	}
	
	
	@RequestMapping("/getHistoryMessage")
	public String getHistoryMessage(MessageVo messageVo){
		
		
		List<MessageVo> voList = this.fileService.getMessage(messageVo, null, null);
		
		return JsonUtil.objectToJsonString(voList);
	}
	
	
	@RequestMapping("/uploadPortrait")
	public String uploadPortrait(String account,MultipartFile portrait){
		
		User user = this.userService.queryUserByAccount(account);
		String path="/"+user.getAccount()+"_"+new SimpleDateFormat("yyyyMMdd").format(user.getActTime())+"/";
		
		String fileName=portrait.getOriginalFilename();
		
		String suffix=fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
		fileName=user.getAccount()+"_portrait"+suffix;
		
		path=path+fileName;
		
		try {
			InputStream in = portrait.getInputStream();
			
			byte[] total=new byte[0];
			int totalLen=0;
			
			byte[] b=new byte[1024];
			int len=0;
			
			
			while((len=in.read(b))!=-1){
				total = ArrayUtils.addAll(total, b);
				totalLen+=len;
			}
			
			this.fileService.uploadUserPortrait(path,total,totalLen);
			
			SysLabel sysLabel = new SysLabel();
			sysLabel.setPortrait(path);
			sysLabel.setAccount(user.getAccount());
			this.labelService.modifySysLabelPortrait(sysLabel);
			
		} catch (IOException e) {
			
		}
		
		return "";
	}
	
	
	
	
}
