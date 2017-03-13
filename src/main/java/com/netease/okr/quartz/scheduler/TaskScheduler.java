package com.netease.okr.quartz.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.netease.okr.quartz.job.JobProcessor;

public class TaskScheduler {
	final static Logger logger = Logger.getLogger(TaskScheduler.class);

	public static void scheduleTaskAt(JobProcessor jobHandler, Date runAt, List<Integer> ids, Integer userId,
			String group) {
		String taskId = new SimpleDateFormat("yyyyMMddHHmmss").format(runAt);

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// build job
			String jobName = "job_" + taskId;
			String jobGroup = "group" + group;
			// un-schedule a job if it exists.
			scheduler.deleteJob(jobKey(jobName, jobGroup));
			JobDetail job = newJob(TaskJob.class).withIdentity(jobName, jobGroup).build();

			// build trigger
			String triggerName = "trigger_" + taskId;
			String triggerGroup = "group" + group;
			Trigger trigger = newTrigger().withIdentity(triggerName, triggerGroup).startAt(runAt).build();

			// pass params to job
			job.getJobDataMap().put(TaskJob.TASK_ID, taskId);
			job.getJobDataMap().put(TaskJob.JOB_HANDLER, jobHandler);
			job.getJobDataMap().put(TaskJob.IDS, ids);
			job.getJobDataMap().put(TaskJob.USER_ID, userId);

			// tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
			// sched.shutdown(true);
			logger.info("scheduler.start();");

		} catch (SchedulerException e) {
			logger.error("TaskScheduler::scheduleTaskAt(TaskId:" + taskId + ", BeginTime:" + runAt + ") Error."
					+ e.getMessage() + "\n" + e.getStackTrace());
			e.printStackTrace();
		}
	}
}
