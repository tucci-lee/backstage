package com.tuccicode.backstage.domain.core.cache;

/**
 * @author tucci.lee
 */
public interface CacheOperate {

    /**
     * 设置缓存
     *
     * @param key        缓存键
     * @param value      缓存值
     * @param expireTime 过期时间/秒
     * @param <T>        缓存值类型
     */
    <T> void set(String key, T value, int expireTime);

    /**
     * 获取缓存
     *
     * @param key 缓存键
     * @param <T> 缓存值类型
     * @return 缓存值
     */
    <T> T get(String key);

    /**
     * 删除缓存
     *
     * @param key 缓存键
     */
    void delete(String key);
}
