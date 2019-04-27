package com.asiainfo.label.dao;

import java.util.List;

import com.asiainfo.entity.Label;
import com.asiainfo.entity.SysLabel;
import com.asiainfo.vo.FriendItemVo;

public interface LabelDao {
	
	SysLabel selectSysLabelByAccount(String account);
	List<Label> selectLabelByAccount(String account);
	
	List<SysLabel> selectSysLabelPortrait(List<FriendItemVo> list);
	
	int updateSysLabelPortrait(SysLabel sysLabel);
	
	int updateSysLabel(SysLabel sysLabel);
	
	int insertLabel(Label label);
	
	int deleteLabelByAccountAndKey(Label label);
	
	int updateLabelValue(Label label);
	
	String selectSysLabelPortraitOne(String account);
}
