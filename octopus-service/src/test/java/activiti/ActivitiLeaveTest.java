package activiti;

import com.octopus.OctopusApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
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
import java.util.List;
import java.util.Map;

/**
 * 请假流程模拟
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OctopusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivitiLeaveTest {
    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    /**
     * 部署流程定义
     */
    @Test
    public void testDeployment() {
        InputStream inputStream;
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
    public void testStartProcessInstance() {

        // 设置请假信息
        String employee = "张三";
        int days = 4;
        String director = "李四";
        String finance = "王五";
        String teamLeader = "赵六";
        String manager = "鲁七";

        // 创建流程变量（变量名与流程中的每个结点填充表达式一致）
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", employee);
        variables.put("day", days);
        variables.put("director", director);
        variables.put("manager", manager);
        variables.put("finance", finance);
        variables.put("teamLeader", teamLeader);

        // 启动流程实例，并指定business_key（可以唯一标识流程实例的字符串，通常用于流程实例的查询和管理）和变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", "leave_张三_0315_01",variables);
        log.info("流程定义id：" + processInstance.getProcessDefinitionId());
        log.info("流程实例id：" + processInstance.getId());
        log.info("当前活动Id：" + processInstance.getActivityId());
    }


    /**
     * 修改变量
     */
    @Test
    public void updateVariables() {
        runtimeService.setVariable("12a6052b-e9b0-11ee-b8f6-00ff2c9247ca","teamLeader","周泰");
    }


    /**
     * 查询实例
     */
    @Test
    public void queryAndUpdateInstance(){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey("leave_张三_0315_01")
                .singleResult();
        //根据实例名查询
        Assert.assertEquals("leave_张三_0315_01",processInstance.getBusinessKey());
    }



    /**
     * 查询名下的待办任务，并完成
     */
    @Test
    public void queryMyTasks(){
        //查询张三所有的请假流程
        List<Task> list = taskService.createTaskQuery().taskAssignee("张三").processDefinitionKey("leave").list();
        list.forEach(e->{
            //进入下一个节点
            taskService.complete(e.getId());
        });

        List<Task> list_1 = taskService.createTaskQuery().taskAssignee("李四").processDefinitionKey("leave").list();
        list_1.forEach(e->{
            // 添加审批意见为通过
            taskService.addComment(e.getId(), e.getProcessInstanceId(), "通过");
            //进入下一个节点
            taskService.complete(e.getId());


        });
        List<Task> list_2 = taskService.createTaskQuery().taskAssignee("王五").processDefinitionKey("leave").list();
        list_2.forEach(e->{
            taskService.addComment(e.getId(), e.getProcessInstanceId(), "通过");
            //进入下一个节点
            taskService.complete(e.getId());

        });
    }

    /**
     * 查询实例的执行情况
     */
    @Test
    public void queryInstanceState(){
        List<HistoricTaskInstance> historyTask = historyService.createHistoricTaskInstanceQuery().processInstanceBusinessKey("leave_张三_0315_01").list();
        System.out.println(historyTask);
    }

}

