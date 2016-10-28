package com.example.study.taskExecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * author: zf
 * Date: 2016/10/28  17:04
 * Description:
 */
@Service
public class TaskService {
    @Async
    public void excuteTask(Integer i){
        System.out.println("aaa-"+i);
    }
    @Async
    public void excuteTaskPlus(Integer i){
        System.out.println("aaa+1-"+(i+1));
    }
}
