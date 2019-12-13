package com.cutety.util;

import com.cutety.domain.Tab;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Jedis工具类
 */
public final class JedisUtil {
    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));


    }


    /**
     * 获取连接方法
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭Jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

  /*  public static void main(String[] args) {
        Jedis jedis=new Jedis();
        Tab tab = new Tab();
        tab.setId(1);
        tab.setTabName("fun");
        List<Tab> tabc=null;
        jedis.zadd("tabbb",tabc.get(0).getId(),tabc.get(0).getTabName());
        Set<Tuple> tabbb = jedis.zrangeWithScores("tabbb",-0,-1);
        System.out.println(tabbb);

    }*/
}
