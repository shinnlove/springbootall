# springbootall
springboot all demo and integration, including some process engine.

# Spring-Boot Integration with Universal Process Starter

VIP features:

- status machine for process proceeding
- pipeline handler execute in sequence without status change
- initialize, ac, reject trigger after process
- 

重要事情说三遍：

**JDK换成1.8，项目换成Level 8后运行!!!**<br/>
**JDK换成1.8，项目换成Level 8后运行!!!**<br/>
**JDK换成1.8，项目换成Level 8后运行!!!**<br/>


## 1. 项目依赖

### 1.1 Dependency GAV Info

因为商单数据源名称有做特殊处理的原因：
**如果是本项目使用`1.0.2-SNAPSHOT`版本**，**如果是商单项目`commercialOrder`，则使用`1.0.1-SNAPSHOT`版本**。

```xml
<dependency>
    <groupId>com.shinnlove.process</groupId>
    <artifactId>universal-process-spring-boot-starter</artifactId>
    <version>1.0.2-SNAPSHOT</version>
</dependency>
```

### 1.2 DB连接字符串与表结构

**重要：运行项目前先修改连接字符串!!**

运行项目并且正常创建流程，需要连接dev数据库：
修改项目`application.yml`文件中数据源的数据库，变成商单`dev`环境的连接字符串：

如果本地`127.0.0.1`安装了MySQL数据库服务，也可以修改本地连接字符串，并创建数据库和新建两张表：

#### a) 流程状态主表

流程状态主表记录状态机主要状态：

```sql
CREATE TABLE `universal_process` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `process_no` bigint(20) NOT NULL DEFAULT '0' COMMENT '流程编号',
  `parent_process_no` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父流程id -1-没有父流程',
  `process_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '流程所属业务: 1-自助改价流程 2-后台改价父流程 3-后台改价子流程',
  `template_id` int(11) NOT NULL DEFAULT '0' COMMENT '模版id',
  `ref_unique_no` bigint(20) NOT NULL DEFAULT '-1' COMMENT '关联业务唯一No. -1-无关联流程编号',
  `current_status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '当前状态 -1-未初始化',
  `latest_operator_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '最后操作人id, -1-代表系统',
  `latest_operator` varchar(128) NOT NULL DEFAULT '' COMMENT '最后操作人',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_process_no` (`process_no`),
  KEY `ix_mtime` (`mtime`),
  KEY `ix_parent_process_no` (`parent_process_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用流程状态表';
```


#### b) 流程状态日志表

流程状态日志表用来记录流程状态机的状态变迁。

```sql
CREATE TABLE `process_status_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `process_no` bigint(20) NOT NULL DEFAULT '0' COMMENT '流程唯一unique No.',
  `template_id` int(11) NOT NULL DEFAULT '0' COMMENT '模版id',
  `action_id` int(11) NOT NULL DEFAULT '0' COMMENT '动作id',
  `source_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '源状态',
  `destination_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '目标状态',
  `operator_type` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '流程操作人类型',
  `operator_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '流程操作人id',
  `operator` varchar(128) NOT NULL DEFAULT '' COMMENT '流程操作人名称',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '流程操作备注',
  PRIMARY KEY (`id`),
  KEY `idx_process_no_action_id` (`process_no`,`action_id`),
  KEY `idx_template_id_action_id` (`template_id`,`action_id`),
  KEY `idx_mtime` (`mtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程变迁状态记录表';
```


## 2. Xml流程模板

add xml template files under META-INF/process folder:

所有的流程模板都在src/main/resources/META-INF/process文件夹下，
描绘了一类业务的流程状态、变迁原因、和变迁时的动作。

先来对流程模板有一个直观的认识，再看小章节来了解流程模板中标签和Tag的意义：

Entire Xml Template Example:

```xml
<?xml version="1.0" encoding="utf-8"?>
<template id="30001" name="revise_item_order_price_template" desc="单个改价项订单金额子流程" parent="30000">

    <metadata>
        <status no="-1" seq="1" name="init" desc="流程初始化" ps="-1"/>
        <status no="1" seq="2" name="risk_audit" desc="待风控审核" ps="1" default="true"/>
        <status no="2" seq="3" name="customer_confirm" desc="待客户确认" ps="2"/>
        <status no="4" seq="4" name="accepted" desc="改价项已完成" ps="4" ac="1"/>
        <status no="5" seq="5" name="rejected" desc="改价项已拒绝" ps="5"/>
        <status no="6" seq="6" name="canceled" desc="改价项已取消" ps="6"/>
    </metadata>

    <init>
        <pre>
            <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        </pre>
        <dispatch>
            <destination no="1">
                <handler seq="1" ref="notifyRiskAuditHandler" desc="通知风控审核"/>
            </destination>
            <destination no="2">
                <handler seq="1" ref="notifyCrmSyncHandler" desc="通知客户审核"/>
            </destination>
        </dispatch>
        <post>
            <handler seq="1" ref="notifyAdvertiserConfirmHandler" desc="通知客户审核"/>
        </post>
    </init>

    <accepted>
        <handler seq="1" ref="acceptProcessHandler" desc="单个改价项完成后trigger"/>
    </accepted>

    <rejected>
        <handler seq="1" ref="rejectProcessHandler" desc="单个改价项拒绝后trigger"/>
    </rejected>

    <canceled>
        <handler seq="1" ref="cancelProcessHandler" desc="单个改价项流程关闭后的trigger"/>
    </canceled>

    <action id="30101" name="order_audit_pass_pending_confirm" desc="订单金额改价项风控审核通过待客户确认" source="1" destination="2">
        <handler seq="1" ref="auditReviseHandler" desc="审核改价项处理器"/>
        <handler seq="2" ref="confirmNotifyHandler" desc="通知客户审核" prepare="30000"/>
    </action>

    <action id="30102" name="order_revise_accomplish" desc="订单金额改价项完成" source="2" destination="4">
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        <handler seq="2" ref="notifyAdvertiserHandler" desc="改价通知客户"/>
    </action>

    <action id="30103" name="order_revise_reject" desc="订单金额改价项拒绝" source="1" destination="5">
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        <handler seq="2" ref="notifyAdvertiserHandler" desc="改价通知客户"/>
    </action>

    <action id="30104" name="order_revise_reject_2" desc="订单金额改价项拒绝" source="2" destination="5">
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        <handler seq="2" ref="notifyAdvertiserHandler" desc="改价通知客户"/>
    </action>

    <action id="30105" name="order_revise_cancel" desc="订单金额改价项取消" source="1" destination="6">
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        <handler seq="2" ref="notifyAdvertiserHandler" desc="改价通知客户"/>
    </action>

    <action id="30106" name="order_revise_cancel_2" desc="订单金额改价项取消" source="2" destination="6">
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
        <handler seq="2" ref="notifyAdvertiserHandler" desc="改价通知客户"/>
    </action>

</template>
```

### 2.1 元数据模板状态

小到一个审核流程，大到一个订单状态，都属于有状态的流程业务。
这里以一次订单改价状态为例：

由运营发起一次订单金额修改，如果触发一定阈值、会需要风控人员先行审核，
如果审核通过，流程会到客户侧等待客户确认金额、并进行订单后续补费支付流程，
如果修改金额在阈值范围内，则不需要风控审核会直接推进到待客户确认。
客户确认或拒绝后，整个订单改价流程走到终态，任何审核阶段都可以直接终止所有流程。

根据这样的业务，我们抽象出如下节点：

- 流程初始化（所有流程未创建时）
- 待风控审核
- 待客户确认
- 改价项已完成
- 改价项已拒绝
- 改价项已取消

```xml
<metadata>
    <status no="-1" seq="1" name="init" desc="流程初始化" ps="-1"/>
    <status no="1" seq="2" name="risk_audit" desc="待风控审核" ps="1" default="true"/>
    <status no="2" seq="3" name="customer_confirm" desc="待客户确认" ps="2"/>
    <status no="4" seq="4" name="accepted" desc="改价项已完成" ps="4" ac="1"/>
    <status no="5" seq="5" name="rejected" desc="改价项已拒绝" ps="5"/>
    <status no="6" seq="6" name="canceled" desc="改价项已取消" ps="6"/>
</metadata>
```

总计6个状态节点，在xml file中，`no`标签节点标记节点的`id`值，
`seq`标签标记节点的顺序值，`desc`表示该节点的描述，
如果有父流程，`ps`标签表示子状态对应父状态抽象为什么状态。


### 2.2 流程初始化

所有的流程都需要初始化，如果是一个报名流程，每一次报名都是同一个模板在内存的实例化，
每个报名编号就是该次实例化的唯一凭证，也就是由`雪花算法服务`生成的流程编号。

流程模板中，使用`init`模块标签标记一个流程将会如何初始化。

```xml
<init>
    <pre>
        <handler seq="1" ref="updateRegisterStatusHandler" desc="更新注册表状态"/>
    </pre>
    <dispatch>
        <destination no="1">
            <handler seq="1" ref="notifyRiskAuditHandler" desc="通知风控审核"/>
        </destination>
        <destination no="2">
            <handler seq="1" ref="notifyCrmSyncHandler" desc="通知客户审核"/>
        </destination>
    </dispatch>
    <post>
        <handler seq="1" ref="notifyAdvertiserConfirmHandler" desc="通知客户审核"/>
    </post>
</init>
```

init整个流程将会在一个事务中执行完毕，流程将会被创建，整体将遵循原子性。
如在创建流程时有任何后续的操作或异步操作，可以使用流程勾子进行业务代码callback，详见`流程callback`章节。


### 2.3 流程生命周期

流程是有生命周期的，在整个流程彻底完成、达到Accomplished状态，或者被驳回达到reject状态，
甚至是被取消进入cancel完结状态，都会有响应的流程勾子被触发，供业务代码做响应的勾子操作。

**但特别注意：流程勾子将不会在。**

```xml
<accepted>
    <handler seq="1" ref="acceptProcessHandler" desc="单个改价项完成后trigger"/>
</accepted>

<rejected>
    <handler seq="1" ref="rejectProcessHandler" desc="单个改价项拒绝后trigger"/>
</rejected>

<canceled>
    <handler seq="1" ref="cancelProcessHandler" desc="单个改价项流程关闭后的trigger"/>
</canceled>
```


### 2.4 Action动作

在状态流程业务中，从一个状态变迁到另一个状态时，会执行一系列的业务代码、达成一系列的业务条件，才算整个状态变迁完成了。
如下是一个Action的具体代码：

```xml
<action id="30101" name="order_audit_pass_pending_confirm" desc="订单金额改价项风控审核通过待客户确认" source="1" destination="2">
    <handler seq="1" ref="auditReviseHandler" desc="审核改价项"/>
    <handler seq="2" ref="confirmNotifyHandler" desc="通知客户确认" prepare="30000"/>
</action>
```

其中`ref`标签所引用的就是当前项目spring context中的`confirmNotifyHandler`服务。


## 3. Handler处理

```java
/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.bilibili.process.integration.service.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bilibili.universal.process.chain.ActionChain;
import com.bilibili.universal.process.interfaces.ActionHandler;
import com.bilibili.universal.process.model.context.ProcessContext;
import com.bilibili.process.integration.service.biz.model.ApproveInfo;
import com.bilibili.process.integration.service.biz.model.ComplexInfo;
import com.bilibili.process.integration.service.biz.model.ParentProcessInfo;
import com.bilibili.universal.util.log.LoggerUtil;

/**
 * @author Tony Zhao
 * @version $Id: ConfirmNotifyHandler.java, v 0.1 2022-02-11 4:14 PM Tony Zhao Exp $$
 */
@Service
public class ConfirmNotifyHandler implements ActionHandler<ApproveInfo, ParentProcessInfo> {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmNotifyHandler.class);

    @Override
    public ParentProcessInfo process(ActionChain chain, ProcessContext<ApproveInfo> context) {

        LoggerUtil.info(logger, "ConfirmNotifyHandler begin to execute, context", context);

        LoggerUtil.info(logger, "发送确认通知");

        String auditResult = results(context, AuditReviseHandler.class);

        LoggerUtil.info(logger, "auditResult=", auditResult);

        ApproveInfo approveInfo = param(context);
        ComplexInfo complexInfo = new ComplexInfo();
        complexInfo.setApproveInfo(approveInfo);

        ParentProcessInfo processInfo = new ParentProcessInfo();
        processInfo.setResult(123456);
        processInfo.setComplexInfo(complexInfo);
        processInfo.setAuditResult(auditResult);

        return processInfo;
    }

}
```


## 4. Biz Service Handler Example:

每个业务都应该为自己的流程开启、推进指定相应的业务入口，该入口与`User Story`一一对应。


其中，`SnowflakeIdWorker`是每个业务服务必备的唯一id生成器，状态机的流程推进都依赖于该服务生成的唯一`id`。

`initProcess`将会创建出一个流程。

`proceedProcess`将会



一个业务服务应有1~2个`initProcess`方法，多个`proceedProcess`方法。


### 4.1 改价业务服务


```java
@Service
public class RevisePriceServiceImpl implements RevisePriceService {

    /** logger */
    private static final Logger     logger = LoggerFactory.getLogger(RevisePriceServiceImpl.class);

    /** snowflake id generator */
    @Autowired
    private SnowflakeIdWorker       snowflakeIdWorker;

    /** status machine service */
    @Autowired
    private StatusMachine2ndService statusMachine2ndService;

    /** pipeline service. */
    @Autowired
    private PipelineService         pipelineService;

    @Override
    public long submitRevise(int itemType, BigDecimal before, BigDecimal after, String operator) {
        ReviseInfo reviseInfo = new ReviseInfo(itemType, before, after, 123456, "Tony",
            "create order revise");
        ApproveInfo approveInfo = new ApproveInfo(1, 123456, operator, "This is remark.");
        ComplexInfo complexInfo = new ComplexInfo(reviseInfo, approveInfo);

        long uniqueBizNo = snowflakeIdWorker.nextId();
        DataContext<ComplexInfo> dataContext = new DataContext<>(complexInfo);

        long processNo = statusMachine2ndService.initProcess(itemType, uniqueBizNo, dataContext);

        LoggerUtil.info(logger, "Process initialized, processNo=", processNo);

        return processNo;
    }

    @Override
    public long auditRevise(int actionId, long refUniqueNo, int approve, String operator) {
        ApproveInfo info = new ApproveInfo(approve, 123456, operator, "This is remark.");
        DataContext<ApproveInfo> dataContext = new DataContext<>(info);

        ProcessContext context = statusMachine2ndService.proceedProcess(actionId, refUniqueNo,
            dataContext);

        LoggerUtil.info(logger, "Process proceeded, context=", context);

        return 1L;
    }

    @Override
    public Object pipelineAudit(int actionId) {
        Object result = pipelineService.doPipeline(actionId);
        return result;
    }

}
```

### 4.2 状态机衔接

#### 4.2.1 流程创建


```java
long processNo = statusMachine2ndService.initProcess(itemType, uniqueBizNo, dataContext);
```

其中`initProcess`的`模板id`、`业务唯一No`、`业务数据模型`。



#### 4.2.2 流程推进


```java
ProcessContext context = statusMachine2ndService.proceedProcess(actionId, refUniqueNo,
    dataContext);
```


其中`proceedProcess`第一个参数是要推进的`actionId`、`业务唯一No`、`业务数据模型`。


### 4.3 Process Callback

如果想要在流程业务代码中执行Callback，可以直接使用callback钩子，代码如下：

```java
/**
 * Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.bilibili.universal.process.callback;

import com.bilibili.universal.process.model.context.ProcessContext;

/**
 * A process callback used by business caller for receive response data and do business in status machine's transaction.
 *
 * Special warning: all codes executed in doCallback method is included in the whole transaction!
 *
 * @author Tony Zhao
 * @version $Id: ProcessCallBack.java, v 0.1 2021-07-28 2:45 PM Tony Zhao Exp $$
 */
@FunctionalInterface
public interface ProcessCallback {

    /**
     * status machine use this method to pass back data context resp to business caller.
     *
     * @param resp
     */
    void doCallback(final ProcessContext resp);

}

```

无论是`initProcess`还是`proceedProcess`都会有这个钩子。



## 5. Demo in Controller


```java
@RestController
@RequestMapping("/status")
public class StatusMachineController {

    @Autowired
    private RevisePriceService revisePriceService;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initProcess() {
        int id = TemplateType.ORDER_PRICE.getTemplateId();
        BigDecimal before = new BigDecimal(127.00);
        BigDecimal after = new BigDecimal(315.00);

        long result = revisePriceService.submitRevise(id, before, after, "tony");

        return String.valueOf(result);
    }

    @RequestMapping("/proceed/{id}")
    public String proceedProcess(@PathVariable(value = "id") long refUniqueNo) {
        int actionId = ActionType.ORDER_PENDING_CONFIRM.getActionId();

        long result = revisePriceService.auditRevise(actionId, refUniqueNo, 1, "Tony酱");

        return String.valueOf(result);
    }

    @RequestMapping("/pipeline")
    public String pipelineProcess() {
        int actionId = ActionType.ORDER_PENDING_CONFIRM.getActionId();

        Object result = revisePriceService.pipelineAudit(actionId);

        return JSON.toJSONString(result);
    }

}
```

启动项目，访问：[http://127.0.0.1:8080/status/init](http://127.0.0.1:8080/status/init)尝试项目流程创建与推进。








