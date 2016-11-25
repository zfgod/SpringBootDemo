/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.22-log : Database - springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `batch_job_execution` */

DROP TABLE IF EXISTS `batch_job_execution`;

CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution` */

insert  into `batch_job_execution`(`JOB_EXECUTION_ID`,`VERSION`,`JOB_INSTANCE_ID`,`CREATE_TIME`,`START_TIME`,`END_TIME`,`STATUS`,`EXIT_CODE`,`EXIT_MESSAGE`,`LAST_UPDATED`,`JOB_CONFIGURATION_LOCATION`) values (1,2,1,'2016-11-11 12:17:07','2016-11-11 12:17:07','2016-11-11 12:17:24','FAILED','FAILED','','2016-11-11 12:17:24',NULL),(2,2,1,'2016-11-11 12:22:53','2016-11-11 12:22:53','2016-11-11 12:23:07','COMPLETED','COMPLETED','','2016-11-11 12:23:07',NULL),(3,2,2,'2016-11-11 12:26:17','2016-11-11 12:26:17','2016-11-11 12:26:28','COMPLETED','COMPLETED','','2016-11-11 12:26:28',NULL),(4,2,3,'2016-11-11 12:30:41','2016-11-11 12:30:41','2016-11-11 12:30:56','COMPLETED','COMPLETED','','2016-11-11 12:30:56',NULL),(5,2,4,'2016-11-11 12:32:31','2016-11-11 12:32:32','2016-11-11 12:32:32','COMPLETED','COMPLETED','','2016-11-11 12:32:32',NULL),(6,2,5,'2016-11-11 14:37:49','2016-11-11 14:37:49','2016-11-11 14:37:52','FAILED','FAILED','','2016-11-11 14:37:52',NULL),(7,2,6,'2016-11-11 16:07:34','2016-11-11 16:07:34','2016-11-11 16:09:16','COMPLETED','COMPLETED','','2016-11-11 16:09:26',NULL),(8,2,7,'2016-11-11 16:18:26','2016-11-11 16:18:27','2016-11-11 16:21:43','COMPLETED','COMPLETED','','2016-11-11 16:21:53',NULL),(9,2,8,'2016-11-11 17:17:09','2016-11-11 17:17:09','2016-11-11 17:17:30','COMPLETED','COMPLETED','','2016-11-11 17:17:33',NULL),(10,2,9,'2016-11-11 17:26:20','2016-11-11 17:26:20','2016-11-11 17:26:20','COMPLETED','COMPLETED','','2016-11-11 17:26:20',NULL),(11,2,10,'2016-11-11 17:28:00','2016-11-11 17:28:00','2016-11-11 17:28:01','FAILED','FAILED','','2016-11-11 17:28:01',NULL);

/*Table structure for table `batch_job_execution_context` */

DROP TABLE IF EXISTS `batch_job_execution_context`;

CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_context` */

insert  into `batch_job_execution_context`(`JOB_EXECUTION_ID`,`SHORT_CONTEXT`,`SERIALIZED_CONTEXT`) values (1,'{\"map\":[\"\"]}',NULL),(2,'{\"map\":[\"\"]}',NULL),(3,'{\"map\":[\"\"]}',NULL),(4,'{\"map\":[\"\"]}',NULL),(5,'{\"map\":[\"\"]}',NULL),(6,'{\"map\":[\"\"]}',NULL),(7,'{\"map\":[\"\"]}',NULL),(8,'{\"map\":[\"\"]}',NULL),(9,'{\"map\":[\"\"]}',NULL),(10,'{\"map\":[\"\"]}',NULL),(11,'{\"map\":[\"\"]}',NULL);

/*Table structure for table `batch_job_execution_params` */

DROP TABLE IF EXISTS `batch_job_execution_params`;

CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_params` */

insert  into `batch_job_execution_params`(`JOB_EXECUTION_ID`,`TYPE_CD`,`KEY_NAME`,`STRING_VAL`,`DATE_VAL`,`LONG_VAL`,`DOUBLE_VAL`,`IDENTIFYING`) values (1,'LONG','run.id','','1970-01-01 08:00:00',1,0,'Y'),(1,'STRING','-spring.output.ansi.enabled','always','1970-01-01 08:00:00',0,0,'N'),(2,'LONG','run.id','','1970-01-01 08:00:00',1,0,'Y'),(2,'STRING','-spring.output.ansi.enabled','always','1970-01-01 08:00:00',0,0,'N'),(3,'LONG','run.id','','1970-01-01 08:00:00',2,0,'Y'),(3,'STRING','-spring.output.ansi.enabled','always','1970-01-01 08:00:00',0,0,'N'),(4,'LONG','run.id','','1970-01-01 08:00:00',3,0,'Y'),(4,'STRING','-spring.output.ansi.enabled','always','1970-01-01 08:00:00',0,0,'N'),(5,'LONG','run.id','','1970-01-01 08:00:00',4,0,'Y'),(5,'STRING','-spring.output.ansi.enabled','always','1970-01-01 08:00:00',0,0,'N'),(6,'LONG','time','','1970-01-01 08:00:00',1478846268020,0,'Y'),(6,'STRING','input.file.name','null.csv','1970-01-01 08:00:00',0,0,'Y'),(7,'LONG','time','','1970-01-01 08:00:00',1478851647102,0,'Y'),(7,'STRING','input.file.name','back/people2.csv','1970-01-01 08:00:00',0,0,'Y'),(8,'LONG','time','','1970-01-01 08:00:00',1478852258768,0,'Y'),(8,'STRING','input.file.name','back/people2.csv','1970-01-01 08:00:00',0,0,'Y'),(9,'LONG','time','','1970-01-01 08:00:00',1478855811113,0,'Y'),(9,'STRING','input.file.name','people.csv','1970-01-01 08:00:00',0,0,'Y'),(10,'LONG','time','','1970-01-01 08:00:00',1478856379928,0,'Y'),(10,'STRING','input.file.name','people.csv','1970-01-01 08:00:00',0,0,'Y'),(11,'LONG','time','','1970-01-01 08:00:00',1478856479910,0,'Y'),(11,'STRING','input.file.name','people.csv','1970-01-01 08:00:00',0,0,'Y');

/*Table structure for table `batch_job_execution_seq` */

DROP TABLE IF EXISTS `batch_job_execution_seq`;

CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_execution_seq` */

insert  into `batch_job_execution_seq`(`ID`,`UNIQUE_KEY`) values (11,'0');

/*Table structure for table `batch_job_instance` */

DROP TABLE IF EXISTS `batch_job_instance`;

CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_instance` */

insert  into `batch_job_instance`(`JOB_INSTANCE_ID`,`VERSION`,`JOB_NAME`,`JOB_KEY`) values (1,0,'importJob','853d3449e311f40366811cbefb3d93d7'),(2,0,'importJob','e070bff4379694c0210a51d9f6c6a564'),(3,0,'importJob','a3364faf893276dea0caacefbf618db5'),(4,0,'importJob','47c0a8118b74165a864b66d37c7b6cf5'),(5,0,'importJob','d71eef6e41d91fb80a6940734f34e23c'),(6,0,'importJob','3400d9418b3b7da3e1d6042be4395ff3'),(7,0,'importJob','7e2de411f8322e6a4148e229a09f99d5'),(8,0,'importJob','8735f8184a7205a19c49fb6396f4929c'),(9,0,'importJob','17593990b701ae52b4df10db8bfce325'),(10,0,'importJob','82ed791b287bb5d601e48c67ef8792b4');

/*Table structure for table `batch_job_seq` */

DROP TABLE IF EXISTS `batch_job_seq`;

CREATE TABLE `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_job_seq` */

insert  into `batch_job_seq`(`ID`,`UNIQUE_KEY`) values (10,'0');

/*Table structure for table `batch_step_execution` */

DROP TABLE IF EXISTS `batch_step_execution`;

CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution` */

insert  into `batch_step_execution`(`STEP_EXECUTION_ID`,`VERSION`,`STEP_NAME`,`JOB_EXECUTION_ID`,`START_TIME`,`END_TIME`,`STATUS`,`COMMIT_COUNT`,`READ_COUNT`,`FILTER_COUNT`,`WRITE_COUNT`,`READ_SKIP_COUNT`,`WRITE_SKIP_COUNT`,`PROCESS_SKIP_COUNT`,`ROLLBACK_COUNT`,`EXIT_CODE`,`EXIT_MESSAGE`,`LAST_UPDATED`) values (1,2,'step1',1,'2016-11-11 12:17:08','2016-11-11 12:17:23','FAILED',0,5,0,0,0,0,0,1,'FAILED','org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [insert into people (id,name,age,nation,address)values(null, ?, ?, ?, ?)]; nested exception is java.sql.BatchUpdateException: Table \'springboot.people\' doesn\'t exist\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:231)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)\r\n	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:649)\r\n	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:662)\r\n	at org.springframework.jdbc.core.JdbcTemplate.batchUpdate(JdbcTemplate.java:950)\r\n	at org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils.executeBatchUpdateWithNamedParameters(NamedParameterBatchUpdateUtils.java:40)\r\n	at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.batchUpdate(NamedParameterJdbcTemplate.java:335)\r\n	at org.springframework.batch.item.database.JdbcBatchItemWriter.write(JdbcBatchItemWriter.java:181)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:175)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:151)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:274)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:199)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:406)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:330)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:133)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:271)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:81)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:374)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.Re','2016-11-11 12:17:23'),(2,3,'step1',2,'2016-11-11 12:22:53','2016-11-11 12:23:07','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 12:23:07'),(3,3,'step1',3,'2016-11-11 12:26:17','2016-11-11 12:26:28','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 12:26:28'),(4,3,'step1',4,'2016-11-11 12:30:41','2016-11-11 12:30:56','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 12:30:56'),(5,3,'step1',5,'2016-11-11 12:32:32','2016-11-11 12:32:32','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 12:32:32'),(6,2,'step1',6,'2016-11-11 14:37:50','2016-11-11 14:37:52','FAILED',0,0,0,0,0,0,0,0,'FAILED','org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:147)\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader$$FastClassBySpringCGLIB$$ebb633d0.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:720)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:133)\r\n	at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:121)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:655)\r\n	at org.springframework.batch.item.file.FlatFileItemReader$$EnhancerBySpringCGLIB$$5fb829f1.open(<generated>)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:96)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:310)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:197)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:64)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:134)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:306)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:135)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:128)\r\n	at com.example.study.batch.DemoController.imp(DemoC','2016-11-11 14:37:52'),(7,3,'step1',7,'2016-11-11 16:07:34','2016-11-11 16:09:16','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 16:09:16'),(8,3,'step1',8,'2016-11-11 16:18:51','2016-11-11 16:21:43','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 16:21:43'),(9,3,'step1',9,'2016-11-11 17:17:11','2016-11-11 17:17:30','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 17:17:30'),(10,3,'step1',10,'2016-11-11 17:26:20','2016-11-11 17:26:20','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2016-11-11 17:26:20'),(11,2,'step1',11,'2016-11-11 17:28:01','2016-11-11 17:28:01','FAILED',0,0,0,0,0,0,0,0,'FAILED','org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:147)\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader$$FastClassBySpringCGLIB$$ebb633d0.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:720)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n	at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:133)\r\n	at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:121)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:655)\r\n	at org.springframework.batch.item.file.FlatFileItemReader$$EnhancerBySpringCGLIB$$ca71ca9e.open(<generated>)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:96)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:310)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:197)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:64)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:134)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:306)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:135)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:128)\r\n	at com.example.study.batch.DemoController.importDat','2016-11-11 17:28:01');

/*Table structure for table `batch_step_execution_context` */

DROP TABLE IF EXISTS `batch_step_execution_context`;

CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution_context` */

insert  into `batch_step_execution_context`(`STEP_EXECUTION_ID`,`SHORT_CONTEXT`,`SERIALIZED_CONTEXT`) values (1,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":0},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(2,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(3,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(4,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(5,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(6,'{\"map\":[\"\"]}',NULL),(7,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(8,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(9,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(10,'{\"map\":[{\"entry\":[{\"string\":[\"batch.taskletType\",\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\"]},{\"string\":\"FlatFileItemReader.read.count\",\"int\":6},{\"string\":[\"batch.stepType\",\"org.springframework.batch.core.step.tasklet.TaskletStep\"]}]}]}',NULL),(11,'{\"map\":[\"\"]}',NULL);

/*Table structure for table `batch_step_execution_seq` */

DROP TABLE IF EXISTS `batch_step_execution_seq`;

CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `batch_step_execution_seq` */

insert  into `batch_step_execution_seq`(`ID`,`UNIQUE_KEY`) values (11,'0');

/*Table structure for table `people` */

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `nation` varchar(40) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `people` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `ScoreSum` varchar(100) DEFAULT NULL,
  `ScoreAvg` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`Id`,`name`,`age`,`ScoreSum`,`ScoreAvg`) values (1,'a',45,'1364','312'),(2,'a',19,'1364','312'),(3,'a',19,'1364','312');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `qq` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`Id`,`name`,`age`,`qq`,`phone`) values (1,'a1',45,'1364','312'),(2,'a2_update',29,'136234','31212'),(3,'a3_update',39,'136234','31212'),(4,'a4_1107',49,'13621134','3111212'),(5,'a5_1107',59,'13621134','53111212'),(6,'a6-dd',16,'1364','312'),(7,'a7_aaaddda7',66,'13621134','3111212'),(8,'a8-dd',1,'1364','312');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
