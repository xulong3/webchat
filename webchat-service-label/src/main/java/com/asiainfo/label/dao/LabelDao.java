package com.asiainfo.label.dao;

import java.util.List;

import com.asiainfo.entity.Label;
import com.asiainfo.entity.SysLabel;

public interface LabelDao {
	
	SysLabel selectSysLabelByAccount(String account);
	List<Label> selectLabelByAccount(String account);
}
