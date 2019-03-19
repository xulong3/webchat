package com.asiainfo.label.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.asiainfo.entity.SysLabel;
import com.asiainfo.label.dao.LabelDao;
import com.asiainfo.label.dao.SysLabelDao;
import com.asiainfo.label.service.LabelService;

public class LabelServiceImpl implements LabelService{

	@Resource
	private LabelDao labelDao;
	@Resource
	private SysLabelDao sysLabelDao;
	
	
	@Override
	public List<SysLabel> querySysLabel() {
		return this.sysLabelDao.selectSysLabel();
	}

}
