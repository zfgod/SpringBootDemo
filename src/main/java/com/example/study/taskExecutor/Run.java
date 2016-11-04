package com.example.study.taskExecutor;

import com.example.study.aware.AwareConfig;
import com.example.study.aware.AwareDemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: zf
 * Date: 2016/10/28  16:34
 * Description:
 */
public class Run {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        TaskService bean = context.getBean(TaskService.class);
        for(int i =0;i<10;i++){
            bean.excuteTask(i);
            bean.excuteTaskPlus(i);
        }
        context.close();
    }
}
