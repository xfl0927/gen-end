package com.jc.platform.model.dto;

import lombok.Data;

/**
 * ClassName PlatformModelApplication
 * Description {this.desc!''}
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Data
public class WorkflowDto {
    /**
     * 流程实例ID
     */
    private String processInstanceId;
    /**
     * 原因
     */
    private String reason;
    /**
     * 业务ID
     */
    private Long id;
}
