package com.jc.platform.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName IsWorkflowEditField
 * Description 工作流详情展示字段标识
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsWorkflowEditField {
    String value() default "";
}
