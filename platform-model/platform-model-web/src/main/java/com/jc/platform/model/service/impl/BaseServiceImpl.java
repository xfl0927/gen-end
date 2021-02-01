package com.jc.platform.model.service.impl;

import com.jc.platform.common.result.PageInfo;
import com.jc.platform.redis.utils.RedisUtils;
import com.jc.platform.model.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * ClassName DictionaryServiceImpl
 * Description 数据字典逻辑层
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Slf4j
@Service
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Resource
    private Environment environment;
    @Resource
    private RedisUtils<String, T> objectRedisUtils;
    @Resource
    private RedisUtils<String, List<T>> listRedisUtils;
    @Resource
    private RedisUtils<String, PageInfo<T>> pageRedisUtils;
    // 默认超时时间（单位秒）
    private static final Integer timeout = 300;


    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param object    实体
     */
    @Override
    public void setRedis(String redisKey , T object){
        if(redisCache() && object!=null){
            objectRedisUtils.set(redisKey, object, timeOut());
        }
    }

    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param list      集合
     */
    @Override
    public void setRedis(String redisKey , List<T> list){
        if(redisCache() && list!=null && !list.isEmpty()){
            listRedisUtils.lSet(redisKey, list, timeOut());
        }
    }

    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param page      分页对象
     */
    @Override
    public void setRedis(String redisKey , PageInfo<T> page){
        if(redisCache() && page!=null && page.getList()!=null && !page.getList().isEmpty()){
            pageRedisUtils.set(redisKey, page, timeOut());
        }
    }

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return <T> T
     */
    @Override
    public T getRedis(String redisKey){
        if(!objectRedisUtils.hasKey(redisKey)){
            return null;
        }
        return objectRedisUtils.get(redisKey);
    }

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return List<T>
     */
    @Override
    public List<T> getRedisForList(String redisKey){
        if(!objectRedisUtils.hasKey(redisKey)){
            return null;
        }
        return listRedisUtils.lGet(redisKey, 0, -1).get(0);
    }

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return PageInfo<T>
     */
    @Override
    public PageInfo<T> getRedisForPage(String redisKey){
        if(!objectRedisUtils.hasKey(redisKey)){
            return null;
        }
        return pageRedisUtils.get(redisKey);
    }

    /***
     * 删除缓存Key
     *
     * @param redisKey 缓存Key
     */
    @Override
    public void delRedisKey(String redisKey){
        CompletableFuture.runAsync(() -> {
            try {
                objectRedisUtils.del(redisKey);
            }catch (Exception e){
                log.error("缓存删除线程错误", e);
                Thread.currentThread().interrupt();
            }
        });
    }

    /***
     * 批量删除缓存Key
     *
     * @param keyPrefix 缓存Key前缀
     */
    @Override
    public void delRedisKeyBatch(String keyPrefix){
        CompletableFuture.runAsync(() -> {
            try {
                String keyPattern = RedisUtils.getRedisKey(keyPrefix, "collection:*");
                Set<String> keys = objectRedisUtils.keys(keyPattern);
                if (keys != null){
                    String[] keyArray = keys.toArray(new String[] {});
                    objectRedisUtils.del(keyArray);
                }
            }catch (Exception e){
                log.error("缓存删除线程错误", e);
                Thread.currentThread().interrupt();
            }
        });
    }

    /***
     * 获取Redis缓存开关
     *
     * @return Boolean
     */
    private Boolean redisCache(){
        Boolean cache = environment.getProperty("platform.redis-cache",Boolean.class);
        return cache != null && cache;
    }

    /***
     * 获取Redis缓存的超时时间
     *
     * @return Integer
     */
    private Integer timeOut(){
        Integer timer = environment.getProperty("platform.time-out",Integer.class);
        return timer != null && timer > 0 ? timer : timeout;
    }
}
