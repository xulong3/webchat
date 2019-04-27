package com.asiainfo.message.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.entity.FriendDynamic;
import com.asiainfo.message.dao.FriendDynamicDao;
import com.asiainfo.message.service.FriendDynamicService;


@Transactional
public class FriendDynamicServiceImpl implements FriendDynamicService{

	@Resource
	private FriendDynamicDao friendDynamicDao;

	@Override
	public String saveFriendDynamic(FriendDynamic friendDynamic) {
		int rows = this.friendDynamicDao.insertFriendDynamic(friendDynamic);
		return "yes";
	}

	@Override
	public List<FriendDynamic> queryFriendDynamicSelf(String account) {
		
		return this.friendDynamicDao.selectFriendDynamicByAccount(account);
	}

	@Override
	public List<FriendDynamic> queryFriendDynamicAll(List<Map<String, String>> list) {
		List<FriendDynamic> allDynamic=new ArrayList<FriendDynamic>();
		
		for (Map<String, String> map : list) {
			String account = map.get("account");
			String friendCircle = map.get("friendCircle");
			List<FriendDynamic> dynamics = this.friendDynamicDao.selectFriendDynamicByAccount(account);
			//只可见三天之内的
			if(friendCircle.equals("1")){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -3);
				long time = c.getTime().getTime();
				
				ListIterator<FriendDynamic> iterator = dynamics.listIterator();
				while(iterator.hasNext()){
					FriendDynamic fd = iterator.next();
					if(fd.getPublishTime().getTime()<time){
						iterator.remove();
					}
				}
			}
			//最终的单个account的FriendDynamic实体
			allDynamic.addAll(dynamics);
		}
		return allDynamic;
	}

	@Override
	public String modifyFriendDynamicPraiseCount(String dynamicId) {
		int rows = this.friendDynamicDao.updateFriendDynamicPraiseCount(dynamicId);
		return "yes";
	}

	@Override
	public String modifyFriendDynamicPraiseCountMinus(String dynamicId) {
		int rows = this.friendDynamicDao.updateFriendDynamicPraiseCountMinus(dynamicId);
		return "yes";
	}


	
}
