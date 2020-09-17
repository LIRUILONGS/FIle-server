/**
 * <b>package-info不是平常类，其作用有三个:</b><br>
 * 1、为标注在包上Annotation提供便利；<br>
 * 2、声明包的私有类和常量；<br>
 * 3、提供包的整体注释说明。<br>
 *
 * <per>
 * <p>1.ConcurrentCache: Tomcat中的一个缓存处理工具类，线程安全，基于WeakHashMap实现LRU策略缓存处理<p/>
 * <p>2.LinkedHashMapCache：基于 LinkedHashMap 的缓存工具类，基于volatile 实现LRU策略线程安全缓存 <p/>
 * <p>3.WeakHashMapCache: 基于 WeakHashMap 的缓存工具类，基于volatile 实现LRU策略线程安全缓存<p/>
 * <per/>
 *
 * @Description 本地缓存工具包
 * @author Liruilong
 * @Date 2020年08月12日  11:08:19
 **/

/**
 *   <per>
 *  <p>1.ConcurrentCache: Tomcat中的一个缓存处理工具类，线程安全，基于WeakHashMap实现LRU策略缓存处理<p/>
 *  <p>2.LinkedHashMapCache：基于 LinkedHashMap 的缓存工具类，基于volatile 实现LRU策略线程安全缓存 <p/>
 *  <p>3.WeakHashMapCache: 基于 WeakHashMap 的缓存工具类，基于volatile 实现LRU策略线程安全缓存<p/>
 *   <per/>
 * @Description 本地缓存工具包
 * @author Liruilong
 * @Date 2020年08月12日  11:08:19
 **/
package com.liruilong.fileserver.common.util.concurrentcache;

class DemoCache {

    public static void main(String[] args) {

        ConcurrentCache cache = new ConcurrentCache(3);
        LinkedHashMapCache cache1 = new LinkedHashMapCache.Builder<String, Integer>(3).build();
        WeakHashMapCache cache2 = new WeakHashMapCache.Builder<String, String>(3).build();

        for (int i = 0; i < 5; i++) {
            cache.put(i + "", i + "");
        }
        System.gc();
        for (int i = 0; i < 5; i++) {
            System.out.println(cache.get(i + ""));
        }

    }
}