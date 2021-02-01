package com.jc.platform.model.service;

import com.jc.platform.common.result.PageInfo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * ClassName IBaseService
 * Description 基础的服务层
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
public interface IBaseService {

    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param object    实体
     */
    void setRedis(String redisKey , T object);

    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param list      集合
     */
    void setRedis(String redisKey , List<T> list);

    /***
     * 加入缓存
     *
     * @param redisKey  缓存Key
     * @param page      分页对象
     */
    void setRedis(String redisKey , PageInfo<T> page);

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return <T> T
     */
    T getRedis(String redisKey);

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return List<T>
     */
    List<T> getRedisForList(String redisKey);

    /***
     * 获取缓存数据
     *
     * @param redisKey      缓存Key
     *
     * @return PageInfo<T>
     */
    PageInfo<T> getRedisForPage(String redisKey);

    /***
     * 删除缓存Key
     *
     * @param redisKey 缓存Key
     */
    void delRedisKey(String redisKey);

    /***
     * 批量删除缓存Key
     *
     * @param keyPrefix 缓存Key前缀
     */
    void delRedisKeyBatch(String keyPrefix);
}
