package com.asiainfo.label.service;

import java.util.List;

import com.asiainfo.entity.SysLabel;
import com.asiainfo.vo.FriendItemVo;
import com.asiainfo.vo.SysLabelVo;

public interface LabelService {

	
	String loadSysLabelToRedis(String token);
	String loadLabelToRedis(String token);
	String querySysLabelCache(String token);
	String queryLabelCache(String token);
	List<SysLabel> querySysLabelPortrait(List<FriendItemVo> list);
	
	String modifySysLabelPortrait(SysLabel sysLabel);
	String modifySysLabel(SysLabelVo sysLabelVo);
}
