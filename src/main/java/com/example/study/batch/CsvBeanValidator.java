package com.example.study.batch;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * author: zf
 * Date: 2016/11/11  9:23
 * Description:数据校验
 */
public class CsvBeanValidator<T> implements Validator<T>,InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> validate
                = validator.validate(value);//数据校验
        if(validate.size()>0){
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> tConstraintViolation : validate) {
//                "\t" 空格， "\n"换行
                sb.append(tConstraintViolation.getMessage()+"\n");
            }
            throw new ValidationException(sb.toString());
        }
    }
}
