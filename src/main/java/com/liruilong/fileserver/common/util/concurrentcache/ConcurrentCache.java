package com.liruilong.fileserver.common.util.concurrentcache;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <per>
 * <a>https://github.com/apache/tomcat/blob/3e5ce3108e2684bc25013d9a84a7966a6dcd6e14/java/org/apache/tomcat/util/collections/ConcurrentCache.java<a/>
 * <per/>
 *
 * @Author https://github.com/apache/tomcat/blob/3e5ce3108e2684bc25013d9a84a7966a6dcd6e14/java/org/apache/tomcat/util/collections/ConcurrentCache.java
 * @Date 2020/8/11 08:46
 * @Description: org.apache.tomcat.util.collections;工具类，
 * <p>基于WeakHashMap 实现线程安全的缓存</p>
 */
public final class ConcurrentCache<K, V> {

    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (Objects.isNull(v)) {
            synchronized (longterm) {
                v = this.longterm.get(k);
            }
            if (Objects.nonNull(v)) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            synchronized (longterm) {
                this.longterm.putAll(this.eden);
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }


}
