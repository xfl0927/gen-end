package com.jc.platform.model.vo;

import com.jc.platform.common.supers.AbstractVO;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import com.jc.platform.model.group.Update;
import com.jc.platform.model.group.Insert;
import javax.validation.constraints.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.math.*;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.platform.common.utils.DateUtil;


/**
 * ClassName DrinkVO.java
 * Description 
 *
 * @author 平台管理员
 * @version 4.0
 * @date 
 */
@ApiModel(value = "DrinkVO", description = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class DrinkVO extends AbstractVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 实体编码
     */
    public static final String IS_ENTITY_CODE = "7ee83a5d1759485483d35abbbf034948";

    /**
     * 主键
     */
    @ExcelIgnore
    @NotNull(groups = Update.class, message = "主键不能为Null")
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 租户ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    /**
     * 集团ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "集团ID")
    private Long groupId;
    /**
     * 机构ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "机构ID")
    private Long orgId;
    /**
     * 部门ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    @ApiModelProperty(value = "名称")
    private String drinkName;
    /**
     * 创建人ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "创建人ID")
    private Long createId;
    /**
     * 创建人
     */
    @ExcelIgnore
    @ApiModelProperty(value = "创建人")
    private String createName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @ExcelIgnore
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改人ID
     */
    @ExcelIgnore
    @ApiModelProperty(value = "修改人ID")
    private Long modifyId;
    /**
     * 修改人
     */
    @ExcelIgnore
    @ApiModelProperty(value = "修改人")
    private String modifyName;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @ExcelIgnore
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
    /**
     * 扩展字段
     */
    @ExcelIgnore
    @ApiModelProperty(value = "扩展字段")
    private String extension;
    /**
     * 导入数据时集合
     */
    @ExcelIgnore
    @Valid
    List<DrinkVO> importExcelList;


}



