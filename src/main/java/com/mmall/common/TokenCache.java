package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class TokenCache {


    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    /**
     * LRU算法
     * 初始容量1000
     * ，不够就是10000，
     * 有效时间是12h，
     * 找回成功了就赶快修改密码；
     * goava的缓存
     */
    public static void main(String[] args) {
        String s = null;
        /**s.equalsIgnoreCase("21");
         * 异常发生！**/
        "21".equalsIgnoreCase(null);
    }

    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                /**默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.*/
                @Override
                public String load(String s) throws Exception {
                    /**为了避免不必要的nullpointexception;
                     * null调用equals就会异常*/
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        /**把kv放进来*/
        localCache.put(key, value);
    }

    public static String getKey(String key) {
        String value = null;
        try {
            value = localCache.get(key);
            /**没有对应的，返回null*/
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("localCache get error", e);
        }
        return null;
    }
}
