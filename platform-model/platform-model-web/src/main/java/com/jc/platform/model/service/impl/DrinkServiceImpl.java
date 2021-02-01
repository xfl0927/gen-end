package com.jc.platform.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jc.platform.common.annotation.DataIsolation;
import com.jc.platform.common.constants.GlobalConstant;
import com.jc.platform.common.constants.StringPool;
import com.jc.platform.common.model.UserInfo;
import com.jc.platform.common.model.QueryParam;
import com.jc.platform.common.result.PageInfo;
import com.jc.platform.common.result.ResultModel;
import com.jc.platform.common.search.SqlUtils;
import com.jc.platform.common.utils.BeanUtil;
import com.jc.platform.common.utils.RequestUtil;
import com.jc.platform.model.vo.DrinkVO;
import com.jc.platform.model.entity.DrinkEntity;
import com.jc.platform.model.mapper.IDrinkMapper;
import com.jc.platform.model.service.IDrinkService;
import com.jc.platform.mybatis.utils.CollectCovertUtil;
import com.jc.platform.mybatis.utils.ConditionUtil;
import com.jc.platform.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jc.platform.common.utils.CollectionUtil;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName DrinkServiceImpl.java
 * Description 
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Slf4j
@Service
public class DrinkServiceImpl extends BaseServiceImpl<DrinkVO> implements IDrinkService
{
	private static final String KEY_PREFIX = "db_platformModel:t_drink";

	@Resource(type = IDrinkMapper.class)
	private IDrinkMapper drinkMapper;

	@Override
	public ResultModel<DrinkVO> insert(DrinkVO drinkVO)
	{
		DrinkEntity entity = new DrinkEntity();
		entity.copyFormVO(drinkVO);
		drinkMapper.insert(entity);

		drinkVO.setId(entity.getId());
		delRedisKeyBatch(KEY_PREFIX);
		return ResultModel.success(drinkVO);
	}

	@Override
	public ResultModel<DrinkVO> update(DrinkVO drink)
	{
		DrinkEntity entity = new DrinkEntity();
		entity.copyFormVO(drink);

		drinkMapper.updateById(entity);



		drink.setId(entity.getId());
		delRedisKeyBatch(KEY_PREFIX);
		entity = drinkMapper.selectById(entity.getId());
		return ResultModel.success(entity.copyToVO());
	}

	@Override
	public ResultModel<Boolean> delete(Long id)
	{
         int count = 0;
         count = drinkMapper.deleteById(id);
		if (count > 0)
		{
			delRedisKeyBatch(KEY_PREFIX);
			return ResultModel.success(true);
		}
		else
		{
			return ResultModel.success(false);
		}
	}

	@Override
	public ResultModel<Boolean> deleteBatch(List<Long> ids)
	{
        ids.forEach(id -> {
            drinkMapper.deleteById(id);
        });

        delRedisKeyBatch(KEY_PREFIX);
        return ResultModel.success(true);
	}



	@Override
	public Boolean checkNotExists(Long id, String property, String value)
	{
        QueryWrapper<DrinkEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(BeanUtil.newInstance(DrinkEntity.class));
        queryWrapper.eq(SqlUtils.getColumnByProperty(property), value);

        if(id!=null && id>0){
            queryWrapper.ne(StringPool.ID, id);
        }


        return drinkMapper.selectCount(queryWrapper) <= 0;
	}

	@Override
	public ResultModel<DrinkVO> get(Long id)
	{
        String redisKey = RedisUtils.getRedisKey(KEY_PREFIX, id);
        DrinkVO drinkVO = getRedis(redisKey);
        if (drinkVO == null)
        {
            DrinkEntity entity = drinkMapper.selectById(id);
            if (entity == null){
                return ResultModel.failed();
            }
            drinkVO = entity.copyToVO();
            setRedis(redisKey, drinkVO);
        }
        return ResultModel.success(drinkVO);
	}

	@Override
	@DataIsolation(value = { "group_id","org_id","dept_id","create_id"})
	public ResultModel<List<DrinkVO>> find(QueryParam queryParam)
	{
		UserInfo userInfo = RequestUtil.getLoginUser();
		queryParam.getQuery().put("group_id",userInfo.getGroupId());

        String redisKey = RedisUtils.getRedisKey(KEY_PREFIX, "collection:find." + queryParam.toString());
        List<DrinkVO> list = getRedisForList(redisKey);
            if (list == null || list.isEmpty())
            {
                QueryWrapper<DrinkEntity> queryWrapper = ConditionUtil.getQueryWrapper(queryParam, DrinkEntity.class);
                List<DrinkEntity> entities = drinkMapper.selectList(queryWrapper);
                list = CollectCovertUtil.listVO(entities);
                if (list != null && !list.isEmpty())
                {
                    setRedis(redisKey,list);
                }
            }
        return ResultModel.success(list);
	}

	@Override
	@DataIsolation(value = { "group_id","org_id","dept_id","create_id"})
	public ResultModel<PageInfo<DrinkVO>> finds(QueryParam queryParam)
	{
		UserInfo userInfo = RequestUtil.getLoginUser();
		queryParam.getQuery().put("group_id",userInfo.getGroupId());

        String redisKey = RedisUtils.getRedisKey(KEY_PREFIX, "collection:finds." + queryParam.toString());
        PageInfo<DrinkVO> page = getRedisForPage(redisKey);
        if (page == null)
        {
            QueryWrapper<DrinkEntity> queryWrapper = ConditionUtil.getQueryWrapper(queryParam, DrinkEntity.class);
            IPage<DrinkEntity> entityPage = drinkMapper.selectPage(ConditionUtil.getPage(queryParam), queryWrapper);
            page = CollectCovertUtil.pageVO(entityPage);
            setRedis(redisKey,page);
        }
        return ResultModel.success(page);

	}


	@Override
	public ResultModel<List<DrinkVO>> importData(List<DrinkVO> drinkList) {
		List<DrinkEntity> saves = new ArrayList<>();
		List<DrinkVO> returnList = new ArrayList<>();
		for (DrinkVO drink : drinkList) {
			DrinkEntity entity = new DrinkEntity();
			entity.copyFormVO(drink);
			saves.add(entity);
		}
		saves.forEach(entity -> {
			drinkMapper.insert(entity);
			DrinkVO drink = entity.copyToVO();
			returnList.add(drink);
		});
		return ResultModel.success(drinkList);
	}
}
