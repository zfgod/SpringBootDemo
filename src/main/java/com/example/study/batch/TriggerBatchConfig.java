package com.example.study.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * author: zf
 * Date: 2016/11/11  14:03
 * Description: 手动触发batch操作 ,可以关闭掉springBoot默认自动执行的job
 */
@Configuration
@EnableBatchProcessing //开启批量任务
public class TriggerBatchConfig {
    /**
     *
     * @return ItemReader
     * @throws Exception
     */
    @Bean
    @StepScope
    public FlatFileItemReader<People> reader(
            @Value("#{jobParameters['input.file.path']}") String pathToFile,
            @Value("#{jobParameters['path.type']}") Long type)
            throws Exception{
        FlatFileItemReader<People> reader = new FlatFileItemReader<>();//使用FlatFileItemReader读取文件
        if(type.equals(1L)){
            reader.setResource(new ClassPathResource(pathToFile));//设置文件路径
        }else if(type.equals(2L)){
            File file = new File(pathToFile);
            InputStream i = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource( i);
            reader.setResource(inputStreamResource);
        }
        reader.setEncoding("GBK");
        reader.setLineMapper(new DefaultLineMapper<People>() {{//对文件的数据与people模型做对应映射
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age", "nation", "address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<People>() {{
                setTargetType(People.class);
            }});
        }});
        return  reader;
    }

    /**
     *
     * @return ItemProcessor
     */
    @Bean
    public ItemProcessor<People,People> processor(){
        CsvItemProcess process = new CsvItemProcess();//使用自定义实现csvItemProcess
        process.setValidator(csvBeanValidator());//为processor指定校验器
        return process;
    }
    @Bean
    public Validator<People> csvBeanValidator(){
        return new CsvBeanValidator<People>();
    }

    /**
     *
     * @param dataSource
     * @return ItemWriter
     */
    @Bean
    public ItemWriter<People> writer(DataSource dataSource){//Spring 能让容器中已有的bean以参数的形式注入
        //dataSource 直接注入
        //使用JDBC批处理的JdbcBatchItemWriter 写数据到数据库
        JdbcBatchItemWriter<People> writer = new JdbcBatchItemWriter<People>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<People>()
        );
        //设置sql
        String sql = "insert into people "+"(id,name,age,nation,address)" +
//                "values(hibernate_sequence.nextval, :name, :age, :nation, :address)";
                "values(null, :name, :age, :nation, :address)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource,PlatformTransactionManager transactionManager)
            throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource,PlatformTransactionManager transactionManager)
            throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs,Step s1){
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)//为job指定step
                .end()
                .listener(csvJobListener())//绑定监听器
                .build();
    }

    @Bean
    public CsvJobListener csvJobListener(){
        return new CsvJobListener();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,ItemReader<People> reader,
                      ItemWriter<People> writer,ItemProcessor<People,People> processor){
        return stepBuilderFactory.get("step1")
                .<People,People>chunk(65000)//批处理每次提交65000条数据
                .reader(reader) //绑定reader
                .processor(processor)//绑定processor
                .writer(writer) //绑定writer
                .build();
    }
}
