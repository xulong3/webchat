package com.asiainfo.sso.dao;

import java.util.List;

import com.asiainfo.entity.Label;

public interface LabelDao {

	int batchInsertLabel(List<Label> list);
}
