package com.example.study.batch;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    public JobParameters jobParameters;


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
                .addLong("time",System.currentTimeMillis())
                .addString("input.file.name",path)
                .toJobParameters();
        jobLauncher.run(importJob,jobParameters);
        return "ok";
    }

    @RequestMapping(value = "/impCsv")
    public String importDataWithCsv(HttpServletRequest request)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        for (String key : multipartRequest.getMultiFileMap().keySet()) {
            List<MultipartFile> files = multipartRequest.getMultiFileMap().get(key);
            for (MultipartFile file : files) {
                boolean b = judgeFileType(file);
                if(b){
                    String filename = file.getOriginalFilename();
                    jobParameters = new JobParametersBuilder()
                            .addLong("time",System.currentTimeMillis())
                            .addString("input.file.name",filename)
                            .toJobParameters();
                    jobLauncher.run(importJob, jobParameters);
                }
            }
        }
        return "ok";
    }

    /** 判断文件类型*/
    public  boolean judgeFileType(MultipartFile uploadFile){
        // 校验图片格式
        boolean isLegal = false;
        String filename = uploadFile.getOriginalFilename();
        for (String fileType : File_TYPE) {
            if (StringUtils.endsWithIgnoreCase(filename, fileType)) {
                isLegal = true;
                break;
            }
        }
        return isLegal;
    }
}
