package com.netease.okr.quartz.scheduler;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.netease.okr.quartz.job.JobProcessor;

public class TaskJob implements Job {
	final static Logger logger = Logger.getLogger(TaskJob.class);

	static final String TASK_ID = "task_id";
	static final String JOB_HANDLER = "npHandler";
	static final String IDS = "ids";
	static final String USER_ID = "userId";

	public TaskJob() {
	}

	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String taskId = (String) context.getJobDetail().getJobDataMap().get(TASK_ID);
		try {
			JobProcessor jobHandler = (JobProcessor) context.getJobDetail().getJobDataMap().get(JOB_HANDLER);
			List<Integer> empIds = (List<Integer>) context.getJobDetail().getJobDataMap().get(IDS);
			Integer userId = (Integer) context.getJobDetail().getJobDataMap().get(USER_ID);
			if (jobHandler == null) {
				System.out.println("jobHandler==null!!!");
				return;
			}
			jobHandler.execute(empIds, userId);

			jobHandler = null;

		} catch (Exception e) {
			logger.error("Error while excuting Job. taskId:" + (taskId == null ? "" : taskId), e);
			JobExecutionException e2 = new JobExecutionException(e);
			// Quartz will automatically unschedule
			// all triggers associated with this job
			// so that it does not run again
			e2.setUnscheduleAllTriggers(true);
			throw e2;
		}
	}

}
