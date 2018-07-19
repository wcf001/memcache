package com.wcf.lala;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.schooner.MemCached.MemcachedItem;

import java.util.ArrayList;
import java.util.List;

public class MemcacheClient {

    /**
     * 初始化memcache了客户端
     */
    public static void init(){
        String[] servers={"192.168.88.129:11211"};
        SockIOPool pool=SockIOPool.getInstance();
        //设置服务器
        pool.setServers(servers);
        //设置容错
        pool.setFailover(true);
        //设置初始连接数
        pool.setInitConn(10);
        //设置最小连接数
        pool.setMinConn(5);
        //设置最大连接数
        pool.setMaxConn(25);
        //设置连接池维护线程的睡眠时间
        pool.setMaintSleep(30);
        //设置是否使用nagle算法
        pool.setNagle(false);
        //设置socket的读取等待超时时间
        pool.setSocketTO(3000);
        //设置连接心跳检测开关
        pool.setAliveCheck(true);
        //设置hash算法
        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
        pool.initialize();
    }

    public static void main(String[] args) {
        init();
        MemCachedClient memCachedClient=new MemCachedClient();
        boolean f1=memCachedClient.add("key",1);
        boolean f2=memCachedClient.add("key",2);
        boolean f3=memCachedClient.set("key",5);
        boolean f4=memCachedClient.set("www",1);
        boolean f5=memCachedClient.replace("key" ,33);
        boolean f6=memCachedClient.replace("ww" ,"123");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);
        System.out.println(memCachedClient.get("www"));
        String[] keys={"key","w"};
        System.out.println(memCachedClient.getMulti(keys));
        memCachedClient.prepend("ww","lala");
        memCachedClient.append("ww","!!");
        System.out.println(memCachedClient.get("ww"));
        //通过gets获取key的值和值的版本号，
        MemcachedItem item=memCachedClient.gets("ww");
        //用cas方法进行修改,当key对应的版本号与gets获取的版本号（casUnique）相等时，修改值，防止并发修改
        memCachedClient.cas("ww",(String)item.getValue()+1,item.getCasUnique());
        System.out.println(memCachedClient.get("ww"));
        System.out.println(memCachedClient.incr("key",1));
    }
}
