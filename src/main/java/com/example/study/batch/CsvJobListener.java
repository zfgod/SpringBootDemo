package com.example.study.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * author: zf
 * Date: 2016/11/11  9:40
 * Description: job监听
 *
 */
public class CsvJobListener implements JobExecutionListener {
    private static Logger log = LoggerFactory.getLogger(CsvJobListener.class);
    Long start;
    Long end;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        start = System.currentTimeMillis();
        log.info("job-start ");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        end = System.currentTimeMillis();
        log.info("job-end in "+(end-start)+"ms");
    }
}
