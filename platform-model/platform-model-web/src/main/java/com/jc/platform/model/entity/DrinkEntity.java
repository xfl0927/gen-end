package com.jc.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jc.platform.common.supers.AbstractEntity;
import com.jc.platform.model.vo.DrinkVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.math.*;

/**
 * ClassName DrinkEntity.java
 * Description 
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Data
@TableName("drink")
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class DrinkEntity extends AbstractEntity<DrinkVO> implements Serializable
{
	private static final long serialVersionUID = 1L;

     @TableField(value = ("id"))
     private Long id;
     @TableField(value = ("tenant_id"))
     private Long tenantId;
     @TableField(value = ("group_id"))
     private Long groupId;
     @TableField(value = ("org_id"))
     private Long orgId;
     @TableField(value = ("dept_id"))
     private Long deptId;
     @TableField(value = ("drink_name"))
     private String drinkName;
     @TableField(value = ("create_id"))
     private Long createId;
     @TableField(value = ("create_name"))
     private String createName;
     @TableField(value = ("create_time"))
     private Date createTime;
     @TableField(value = ("modify_id"))
     private Long modifyId;
     @TableField(value = ("modify_name"))
     private String modifyName;
     @TableField(value = ("modify_time"))
     private Date modifyTime;
     @TableField(value = ("extension"))
     private String extension;

	/**
	 * 生成vo对象
	 *
	 * @return vo对象
	 */
	public DrinkVO copyToVO()
	{
		DrinkVO vo = new DrinkVO();
        vo.setId(this.id);
        vo.setTenantId(this.tenantId);
        vo.setGroupId(this.groupId);
        vo.setOrgId(this.orgId);
        vo.setDeptId(this.deptId);
        vo.setDrinkName(this.drinkName);
        vo.setCreateId(this.createId);
        vo.setCreateName(this.createName);
        vo.setCreateTime(this.createTime);
        vo.setModifyId(this.modifyId);
        vo.setModifyName(this.modifyName);
        vo.setModifyTime(this.modifyTime);
        vo.setExtension(this.extension);
		return vo;
	}

	/**
	 * 从vo对象更新属性
	 *
	 * @param vo vo对象
	 */
	public void copyFormVO(DrinkVO vo)
	{
        this.setId(vo.getId());
        this.setTenantId(vo.getTenantId());
        this.setGroupId(vo.getGroupId());
        this.setOrgId(vo.getOrgId());
        this.setDeptId(vo.getDeptId());
        this.setDrinkName(vo.getDrinkName());
        this.setCreateId(vo.getCreateId());
        this.setCreateName(vo.getCreateName());
        this.setCreateTime(vo.getCreateTime());
        this.setModifyId(vo.getModifyId());
        this.setModifyName(vo.getModifyName());
        this.setModifyTime(vo.getModifyTime());
        this.setExtension(vo.getExtension());
	}
}
