package com.jc.platform.model.service;

import com.jc.platform.common.model.QueryParam;
import com.jc.platform.common.result.PageInfo;
import com.jc.platform.common.result.ResultModel;
import org.springframework.transaction.annotation.Transactional;
import com.jc.platform.model.vo.DrinkVO;
import com.jc.platform.model.group.Update;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.*;


/**
 * ClassName IDrinkService.java
 * Description 
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Validated
public interface IDrinkService
{
	/**
	 * 保存
	 *
	 * @param drink 实体
	 * @return 对象vo
	 */
	@Transactional(rollbackFor = Exception.class)
	ResultModel<DrinkVO> insert(@Valid DrinkVO drink);

	/**
	 * 修改
	 *
	 * @param drink 实体
	 * @return 对象vo
	 */
	@Transactional(rollbackFor = Exception.class)
	ResultModel<DrinkVO> update(DrinkVO drink);


	/**
	 * 删除
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	@Transactional(rollbackFor = Exception.class)
	ResultModel<Boolean> delete(Long id);

	/**
	 * 批量删除
	 *
	 * @param ids 主键
	 * @return 是否成功
	 */
	@Transactional(rollbackFor = Exception.class)
	ResultModel<Boolean> deleteBatch(List<Long> ids);

	/**
	 * 校验属性是否存在，存在返回false，不存在返回true
	 *
	 * @param id       主键
	 * @param property 属性名称
	 * @param value    属性值
	 * @return 检查结果
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	Boolean checkNotExists(Long id, String property, String value);

	/**
	 * 查询对象
	 *
	 * @param id 主键
	 * @return 对象
	 */
	ResultModel<DrinkVO> get(Long id);

	/**
	 * 查询对象集合
	 *
	 * @param queryParam 查询参数
	 * @return 对象集合
	 */
	ResultModel<List<DrinkVO>> find(QueryParam queryParam);

	/**
	 * 分页查询
	 *
	 * @param queryParam 查询参数
	 * @return 分页对象
	 */
	ResultModel<PageInfo<DrinkVO>> finds(QueryParam queryParam);



	/**
	 * 通过Excel 导入数据
	 *
	 * @param drinkList
	 */
	@Transactional(rollbackFor = Exception.class)
	ResultModel<List<DrinkVO>> importData(@Valid List<DrinkVO> drinkList);
}
