package com.lingdu.project.monitor.job.util;

import org.quartz.JobExecutionContext;
import com.lingdu.project.monitor.job.domain.Job;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author 猛男波波
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception
    {
        JobInvokeUtil.invokeMethod(job);
    }
}