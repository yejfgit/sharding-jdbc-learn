package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.Appendix;

public interface AppendixService {

	public Boolean updateAppendixList(Integer relId,Integer type, List<Appendix> appendixList);

	public Boolean deleteAppendixList(Integer relId, Integer type);
}
