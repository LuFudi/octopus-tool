package com.octopus.interceptor;

import com.alibaba.fastjson.JSON;
import com.octopus.base.BaseEntity;
import com.octopus.dto.UserLoginInfoDTO;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 用于审计字段自动生成
 *
 * @author lfd
 */
@Component("ibatisAuditDataInterceptor")
@Intercepts({@Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})})
@ConditionalOnProperty(
        value = {"init.audit.data.enable"},//只有配置文件中配置了init.audit.data.enable才会生效
        havingValue = "true"
)
public class MybatisInterceptor implements Interceptor {
    //登录信息
    private Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String userInfoJson="{\"userId\":1,\"userName\":\"gkm\",\"avatarFilePath\":\"\",\"companyId\":2,\"masterSign\":false,\"sellerSign\":false,\"operatorSign\":false,\"shopId\":9,\"accountType\":0,\"linkedMallUserId\":0,\"groupId\":9,\"groupShopIds\":[0],\"requestUrl\":\"\"}";
        UserLoginInfoDTO userLoginInfoDTO= JSON.parseObject(userInfoJson,
                UserLoginInfoDTO.class);
        GKSUserContext.bindLoginUserInfo(userLoginInfoDTO);



        Object[] args = invocation.getArgs();
        SqlCommandType sqlCommandType = null;

        for (Object object : args) {
            // 从MappedStatement参数中获取到操作类型
            if (object instanceof MappedStatement) {
                MappedStatement ms = (MappedStatement) object;
                sqlCommandType = ms.getSqlCommandType();
                logger.debug("操作类型： {}", sqlCommandType);
                //只增强插入和修改方法
                if (sqlCommandType != SqlCommandType.INSERT && sqlCommandType != SqlCommandType.UPDATE) {
                    return invocation.proceed();
                }
                continue;
            }
            logger.debug("mybatis arg: {}", object);
            // 单个参数
            if (object instanceof BaseEntity) {
                if (SqlCommandType.INSERT == sqlCommandType) {
                    this.setInsertAuditData(object);
                    continue;
                }
                if (SqlCommandType.UPDATE == sqlCommandType) {
                    this.setUpdateAuditData(object);
                    continue;
                }
            }
            // 兼容批量操作(新版本MyBatis依赖走这个分支)
            if (object instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<ArrayList<Object>> paramMap;
                try{
                    paramMap = (MapperMethod.ParamMap<ArrayList<Object>>) object;
                }catch (Exception e){
                    logger.info("Param Map convert failed");
                    continue;
                }

                String batchKey = "list";
                if (!paramMap.containsKey(batchKey)) {
                    continue;
                }
                this.batchOperationSetAuditData(paramMap.get(batchKey), sqlCommandType);
            }

            // 兼容批量操作(老版本MyBatis依赖走这个分支)
            if (object instanceof DefaultSqlSession.StrictMap) {
                DefaultSqlSession.StrictMap<ArrayList<Object>> paramMap ;
                try{
                    paramMap = (DefaultSqlSession.StrictMap<ArrayList<Object>>) object;
                }catch (Exception e){
                    logger.info("Param StrictMap convert failed");
                    continue;
                }
                String batchKey = "list";
                if (!paramMap.containsKey(batchKey)) {
                    continue;
                }
                this.batchOperationSetAuditData(paramMap.get(batchKey), sqlCommandType);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    private void batchOperationSetAuditData(ArrayList<Object> objs, SqlCommandType sqlCommandType) {
        for (Object obj : objs) {
            if (obj instanceof BaseEntity) {
                if (SqlCommandType.INSERT == sqlCommandType) {
                    this.setInsertAuditData(obj);
                }
                if (SqlCommandType.UPDATE == sqlCommandType) {
                    this.setUpdateAuditData(obj);
                }
            }
        }
    }

    private void setInsertAuditData(Object object) {
        if (!GKSUserContext.isUserLogin()) {
            return;
        }
        BaseEntity entity = (BaseEntity) object;
        entity.init(GKSUserContext.getUserId(), LocalDateTime.now());

    }

    private void setUpdateAuditData(Object object) {
        if (!GKSUserContext.isUserLogin()) {
            return;
        }
        BaseEntity entity = (BaseEntity) object;
        entity.touch(GKSUserContext.getUserId(), LocalDateTime.now());
    }
}
