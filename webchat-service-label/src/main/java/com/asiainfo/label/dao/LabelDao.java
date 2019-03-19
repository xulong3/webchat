package com.asiainfo.label.dao;

import java.util.List;

import com.asiainfo.entity.Label;

public interface LabelDao {
	List<Label> selectLabelByToken(String account);
}
