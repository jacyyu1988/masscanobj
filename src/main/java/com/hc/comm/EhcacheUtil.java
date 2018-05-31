package com.hc.comm;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存处理
 * @author yusj
 * @date 2018/5/17
 */
public class EhcacheUtil {

    public static final String RECORD_CACHE="recordCache";
    public static final String RECORD_CACHE_IP="ip";
    public static final String RECORD_CACHE_DOMAIN="domain";


    public static CacheManager manager = CacheManager.create();

    public static Object get(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return element.getObjectValue();
            }
        }
        return null;
    }

    public static void put(String cacheName, Object key, Object value) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            cache.put(new Element(key, value));
        }
    }

    public static boolean remove(String cacheName, Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            return cache.remove(key);
        }
        return false;
    }

    public static void clean() {
        Cache recordCache = manager.getCache(RECORD_CACHE);
        if (recordCache != null) {
            recordCache.removeAll();
        }
    }


}
