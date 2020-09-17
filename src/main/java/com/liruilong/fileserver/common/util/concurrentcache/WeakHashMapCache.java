package com.liruilong.fileserver.common.util.concurrentcache;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Liruilong
 * @Date 2020/8/11 09:37
 * @Description: 基于 WeakHashMap 的缓存实现
 */
public class WeakHashMapCache<K, V> {
    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    private WeakHashMapCache(Builder<K, V> builder) {
        this.size = builder.size;
        this.eden = builder.eden;
        this.longterm = builder.longterm;
    }

    public static class Builder<K, V> {
        private volatile int size;

        private volatile Map<K, V> eden;

        private volatile Map<K, V> longterm;

        public Builder(int size) {
            this.size = rangeCheck(size, Integer.MAX_VALUE, "缓存容器初始化容量异常");
            this.eden = new ConcurrentHashMap<>(size);
            this.longterm = new WeakHashMap<>(size);
        }

        private static int rangeCheck(int val, int i, String arg) {
            if (val < 0 || val > i) {
                throw new IllegalArgumentException(arg + ":" + val);
            }
            return val;
        }

        public WeakHashMapCache build() {
            return new WeakHashMapCache(this);
        }

    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (Objects.isNull(v)) {
            v = this.longterm.get(k);
            if (Objects.nonNull(v)) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            this.longterm.putAll(this.eden);
            this.eden.clear();
        }
        this.eden.put(k, v);
    }


}
