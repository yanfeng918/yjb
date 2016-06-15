package com.yongjinbao.utils;

import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by fddxiaohui on 2015/9/25.
 */
public class InitJedisPool {
    @Inject
    private   JedisPool jedisPool;
    public static JedisPool jp;

    @PostConstruct
    public void init(){
        this.jp=jedisPool;
    }
}
