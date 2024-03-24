package com.octopus.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lfd
 */
@Slf4j
@Component
public class LeaveSubmitLeaveApplicationListener implements TaskListener {

    private Expression listener;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("监听器执行取值：{}",delegateTask.getTransientVariable("test"));
    }
}
