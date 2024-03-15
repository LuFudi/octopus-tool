package activiti;

import com.octopus.OctopusApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.data.impl.cachematcher.TasksByExecutionIdMatcher;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OctopusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiDemo {
    @Autowired
    ProcessEngine processEngine;

    @Autowired
    TaskService taskService;

    /**
     * 部署流程定义
     */
    @Test
    public void testDeployment() {
//        2、得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream inputStream = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("activiti/leave.bpmn20.xml");
             inputStream =classPathResource.getInputStream();
        }catch (Exception e){
           log.error("文件流生成异常，exception:",e);
           return ;
        }
        //        3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addInputStream("activiti/leave.bpmn20.xml", inputStream)
                .addClasspathResource("activiti/leave.png")
                .name("请假申请流程")
                .key("leave")
                .deploy();

//        4、输出部署信息
        log.info("流程部署id：" + deployment.getId());
        log.info("流程部署名称：" + deployment.getName());
        log.info("流程部署key：" + deployment.getKey());
    }


    /**
     * 创建实例
     */
    @Test
    public void testLeaveProcess() {
        // 获取运行时服务
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 设置请假信息
        String employee = "张三";
        int days = 4;
        String manager = "李四";
        String finance = "王五";
        String teamLeader = "赵六";
        String director = "鲁七";

        // 创建流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", employee);
        variables.put("day", days);
        variables.put("manager", manager);
        variables.put("finance", finance);
        variables.put("teamLeader", teamLeader);
        variables.put("director", director);
        // 启动流程实例，并指定变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", variables);
        log.info("流程定义id：" + processInstance.getProcessDefinitionId());
        log.info("流程实例id：" + processInstance.getId());
        log.info("当前活动Id：" + processInstance.getActivityId());
    }

    public void jjj(){
        Task submitLeaveApplication = taskService.createTaskQuery().processInstanceId("d831e0f4-e2a6-11ee-9bbd-1cbfc037530f").taskName("提交请假申请").singleResult();
        Assert.assertNotNull(submitLeaveApplication);
        Assert.assertEquals("张三", submitLeaveApplication.getAssignee());

    }

}

