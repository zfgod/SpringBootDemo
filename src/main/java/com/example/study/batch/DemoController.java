package com.example.study.batch;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * author: zf
 * Date: 2016/11/11  14:07
 * Description:
 */
@RestController
public class DemoController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job importJob;
    @Value("${importData.File_TYPE}")
    private String[] File_TYPE;
    @Value("${bath.file.repository}")
    private String  bath_file_repository;

    public JobParameters jobParameters;

    @RequestMapping("/imp/{t}")
    public String imp(@PathVariable("t") int t) throws Exception{
        /**
         * @Description:
         * @author: zf
         * @Param: [fileName]
         * @Return: java.lang.String
         * @Date:   2016/11/16
         */
        String path = "";
        switch (t) {
            case 1:
                path = "/back/people2.csv";
                break;
            case 2:
//                path = "D:/myStu/SpringBootDemo/src/main/webapp/148049567019933.csv";
                path = "D:/uploadTest/ab.csv";
                break;
            default:
                path = "people1.csv";
        }
        if(t!=2) {//classpath路径资源
            ClassPathResource classPathResource = new ClassPathResource(path);
            if(classPathResource.exists()){
                jobParameters = new JobParametersBuilder()
                        .addLong("path.type",1L)
                        .addLong("time",System.currentTimeMillis())
                        .addString("input.file.path",path)
                        .toJobParameters();
                jobLauncher.run(importJob,jobParameters);
            }
        }else {//绝对路径资源
            File file = new File(path);
            if(file.exists()){
                String absolutePath = file.getAbsolutePath();
                jobParameters = new JobParametersBuilder()
                        .addLong("path.type",2L)
                        .addLong("time",System.currentTimeMillis())
                        .addString("input.file.path",absolutePath)
                        .toJobParameters();
                jobLauncher.run(importJob,jobParameters);
                return "ok";
            }
        }
        return "no ok";
    }

    /**
     *
     * @param fileName
     * @return
     * @throws Exception
     */

    @RequestMapping("/imp")
    public String imp(@RequestParam("fileName")String fileName) throws Exception{
         /**
          * @Description:
          * @author: zf
          * @Param: [fileName]
          * @Return: java.lang.String
          * @Date:   2016/11/16
          */

        String path = fileName+".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("path.type",1L)
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.path",path)
                .toJobParameters();
        jobLauncher.run(importJob,jobParameters);
        return "ok";
    }

    @RequestMapping(value = "/impCsv")
    public String importDataWithCsv(HttpServletRequest request)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException, IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (String key : fileMap.keySet()) {
            MultipartFile file = fileMap.get(key);
            String dir = bath_file_repository;
            String filePath = judgeFileTypeAndTransfer(file, dir);
            if (StringUtils.isNotBlank(filePath)) {//存入工程成功
                jobParameters = new JobParametersBuilder()
                        .addLong("path.type",2L)
                        .addLong("time", System.currentTimeMillis())
                        .addString("input.file.path", filePath)
                        .toJobParameters();
                jobLauncher.run(importJob, jobParameters);
            }
        }
        return "ok";
    }

    /** 判断文件类型*/
    public  String judgeFileTypeAndTransfer(MultipartFile uploadFile,String dir)
            throws IOException {
        // 校验图片格式
        boolean isLegal = false;
        String filename = uploadFile.getOriginalFilename();
        String fileSuffix = "";
        for (String fileType : File_TYPE) {
            if (StringUtils.endsWithIgnoreCase(filename, fileType)) {
                isLegal = true;
                fileSuffix = fileType;
                break;
            }
        }
        File fileDir = new File(dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        if(isLegal){
            Random random = new Random();
            String filePath = dir+File.separator+System.currentTimeMillis()
                    + random.nextInt(999)+fileSuffix;
            File file = new File(filePath);
            uploadFile.transferTo(file);
            String absolutePath = file.getAbsolutePath();
            return absolutePath;
        }else {
            return null;
        }
    }
}
