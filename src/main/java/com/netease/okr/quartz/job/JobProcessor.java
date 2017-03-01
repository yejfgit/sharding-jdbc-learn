package com.netease.okr.quartz.job;

import java.util.List;

public interface JobProcessor {
	void execute(List<Integer> ids, Integer userId);
}
