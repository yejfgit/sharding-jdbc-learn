package com.netease.okr.quartz.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netease.okr.enums.KeyResultStatusEnum;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.service.OkrService;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.LoggerUtil;

/**
 * hzyejinfu 
 *
 */
@Component
public class UpdateKeyResultStatusTask implements JobProcessor {

	@Autowired
	private OkrService okrService;
	
	@Autowired
	private WeekReportService weekReportService;
	
	

	/**
	 * 手动触发定时任务
	 * */
	@Override
	public void execute(List<Integer> keyResultIds, Integer userId) {
		LoggerUtil.info("UpdateKeyResultStatusTask--begin");
		
		if(keyResultIds!=null&&keyResultIds.size()>0){
			for(Integer keyResultId:keyResultIds){
				List<WeekReport> wrs = weekReportService.getWeekReportListByKeyResultId(keyResultId);
				if(wrs==null||wrs.size()<1){
					int c = okrService.updateKeyResultStatus(keyResultId,KeyResultStatusEnum.STATUS1.getId());
					LoggerUtil.info("updateKeyResultStatus【keyResultId="+keyResultId+";result="+c+"】");
				}else if(wrs!=null||wrs.size()>0){
					int c = okrService.updateKeyResultStatus(keyResultId,KeyResultStatusEnum.STATUS2.getId());
					LoggerUtil.info("updateKeyResultStatus【keyResultId="+keyResultId+";result="+c+"】");
				}
			}
		}
		
		LoggerUtil.info("UpdateKeyResultStatusTask--end");
	}
	
	

}
