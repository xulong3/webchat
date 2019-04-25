package com.asiainfo.common.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.common.service.FileService;
import com.asiainfo.util.service.JdbcService;
import com.asiainfo.util.service.impl.JdbcServiceImpl;
import com.asiainfo.vo.MessageVo;

@Transactional
public class FileServiceImpl implements FileService{

	private JdbcService jdbcService=new JdbcServiceImpl();
	
	@Override
	public String createUserRootDir(String token, String date) {
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String path=value+token+"_"+date;
		
		File file = new File(path);
		boolean b = file.mkdir();
		if(b){
			
			return "yes";
		}else{
			return "no";
		}
	}

	@Override
	public void createP2pChatFile(String userAccount,String friendAccount) {
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		String parentDirPath=value+"p2pchatfiles";
		
		File f = new File(parentDirPath);
		
		if(!f.exists()){
			f.mkdirs();
		}
		
		String fileName1=userAccount+"with"+friendAccount+".txt";
		String fileName2=friendAccount+"with"+userAccount+".txt";
		
		String filePath1=parentDirPath+"/"+fileName1;
		String filePath2=parentDirPath+"/"+fileName2;
		
		File f1 = new File(filePath1);
		File f2 = new File(filePath2);
		
		if(!f1.exists() && !f2.exists()){
			try {
				f1.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public MessageVo saveMessageToFile(MessageVo messageVo) {
		messageVo.setSendTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		//0表示未读
		messageVo.setIsRead(0);
		messageVo.setFilePath(getP2pFilePath(messageVo));
		
		try {
			FileOutputStream out = new FileOutputStream(messageVo.getFilePath(),true);
			out.write((messageVo.getSendTime()+"\n").getBytes("UTF-8"));
			out.write((messageVo.getSender()+"\n").getBytes("UTF-8"));
			out.write((messageVo.getIsRead()+"\n").getBytes("UTF-8"));
			out.write((messageVo.getMessage()+"\n").getBytes("UTF-8"));
			out.write("end!\n".getBytes("UTF-8"));
			out.flush();
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return messageVo;
		
	}

	@Override
	public void uploadUserPortrait(String relativePath,byte[] b,int len) {
		 
		try {
			String value = jdbcService.queryConfigValueByKey("nginx_root");
			
			String localPath=value+relativePath;
			
			
			OutputStream out = new FileOutputStream(localPath);
			
			out.write(b, 0, len);
			
			out.flush();
			
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MessageVo> getMessage(MessageVo messageVo,Long startTime,Long endTime) {
		String filePath = getP2pFilePath(messageVo);
		try {
			
			List<String> lines = new ArrayList<String>();
			String line=null;
			BufferedReader bin = new BufferedReader(new FileReader(filePath));
			while((line=bin.readLine())!=null){
				lines.add(line);
			}
			bin.close();
			
			List<MessageVo> voList = new ArrayList<MessageVo>();
			
			int timeNum=0;
			int senderNum=1;
			int isReaderNum=2;
			String s="";
			MessageVo msg=null;
			for (int i = 0; i < lines.size(); i++) {
				if(i==timeNum){
					msg = new MessageVo();
					s="";
					msg.setSendTime(lines.get(i));
					continue;
				}
				if(i==senderNum){
					msg.setSender(lines.get(i));
					continue;
				}
				if(i==isReaderNum){
					msg.setIsRead(Integer.parseInt(lines.get(i)));
					continue;
				}
				//消息
				
				//结束标志
				if(lines.get(i).equals("end!") && (i+1==lines.size() || !lines.get(i+1).equals("end!"))){
					msg.setMessage(s);
					voList.add(msg);
					timeNum=i+1;
					senderNum=i+2;
					isReaderNum=i+3;
				}else{
					s+=lines.get(i);
				}
				
			}
			
			
			if(startTime==null && endTime==null){
				
				return voList;
			}
			
			//把不在时间范围内的vo对象剔除掉
			ListIterator<MessageVo> iterator = voList.listIterator();
			while(iterator.hasNext()){
				MessageVo vo = iterator.next();
				if(startTime!=null && new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(vo.getSendTime()).getTime()<startTime.longValue()){
					iterator.remove();
				}
				
				if(endTime!=null && new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(vo.getSendTime()).getTime()>endTime.longValue()){
					iterator.remove();
				}
				
			}
			
			
			return voList;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return  null;
		
	}
	
	private String getP2pFilePath(MessageVo messageVo){
		String value=null;
		try {
			value = jdbcService.queryConfigValueByKey("nginx_root");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String fileName1=messageVo.getSender()+"with"+messageVo.getReceiver()+".txt";
		String fileName2=messageVo.getReceiver()+"with"+messageVo.getSender()+".txt";
		
		String path1=value+"p2pchatfiles/"+fileName1;
		String path2=value+"p2pchatfiles/"+fileName2;
		
		File f1 = new File(path1);
		File f2 = new File(path2);
		
		
		if(f1.exists()){
		
			return path1;
		}
		
		if(f2.exists()){
			return path2;
		}
		
		return null;
	}

	@Override
	public List<String> getMessageDays(MessageVo messageVo) {
		String filePath = getP2pFilePath(messageVo);
		List<String> days=new ArrayList<String>();
		try {
			List<String> lines = new ArrayList<String>();
			String line=null;
			BufferedReader bin = new BufferedReader(new FileReader(filePath));
			while((line=bin.readLine())!=null){
				lines.add(line);
			}
			bin.close();
			
			List<MessageVo> voList = new ArrayList<MessageVo>();
			
			int timeNum=0;
			
			for (int i = 0; i < lines.size(); i++) {
				if(i==timeNum && !days.contains(lines.get(i).substring(0,10))){
					days.add(lines.get(i).substring(0,10));
					continue;
				}
				
				if(lines.get(i).equals("end!") && (i+1==lines.size() || !lines.get(i+1).equals("end!"))){
					//当前索引i为结束标志
					timeNum=i+1;
				}
			}
			
			return days;
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		return null;
	}

	@Override
	public List<Map<String, Object>> getMessageGroupTree(MessageVo messageVo) {
		Set<String> set = new HashSet<String>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		
		List<MessageVo> msg = getMessage(messageVo, null, null);
		System.err.println("一共多少条信息"+msg.size());
		for (MessageVo vo : msg) {
			String time = vo.getSendTime().substring(0,10);
			if(!set.contains(time)){
				
				//第一次
				set.add(time);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("text", time);
				map.put("selectable", false);
				
				List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
				Map<String, Object> node = new HashMap<String,Object>();
				node.put("text", vo.getSender()+":"+vo.getMessage());
				node.put("selectable", false);
				
				nodes.add(node);
				map.put("nodes", nodes);
				list.add(map);
			}else{
				for (Map<String, Object> map : list) {
					if(map.get("text").toString().equals(vo.getSendTime().substring(0,10))){
						List<Map<String, Object>> nodes = (List<Map<String, Object>>)map.get("nodes");
						Map<String, Object> node = new HashMap<String,Object>();
						node.put("text", vo.getSender()+":"+vo.getMessage());
						node.put("selectable", false);
						nodes.add(node);
						map.put("nodes", nodes);
						break;
					}
				}
			}
			
			
		}
		
		
		
		return list;
	}
	

	
}
