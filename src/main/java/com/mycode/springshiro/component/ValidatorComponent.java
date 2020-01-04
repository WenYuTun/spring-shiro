package com.mycode.springshiro.component;

import com.mycode.springshiro.pojo.vo.response.ValidationResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author wenyutun
 * @description: 参数校验组件
 * @date: 2019/8/10
 * @version: 1.0
 */
@Component
public class ValidatorComponent implements InitializingBean {

    private Validator validator;

    /**
     * 实现校验方法并返回校验结果
     */

    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> validate = validator.validate(bean);
        if (validate.size() > 0) {
            result.setHasErrors(true);
            validate.forEach(e -> {
                String errMsg = e.getMessage();
                String propertyName = e.getPropertyPath().toString();
                result.getErrorMap().put(propertyName, errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() {
        //将hibernate validator通过工厂初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
