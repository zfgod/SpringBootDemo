package com.example.study.batch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * author: zf
 * Date: 2016/11/11  9:18
 * Description:数据处理
 */
public class CsvItemProcess extends ValidatingItemProcessor<People> {
    @Override
    public People process(People item) throws ValidationException {
        super.process(item);//须执行，才会调用自定义校验
        if(item.getNation().equals("汉族")){//数据处理
            item.setNation("01");
        }else {
            item.setNation("02");
        }
        return item;
    }
}
