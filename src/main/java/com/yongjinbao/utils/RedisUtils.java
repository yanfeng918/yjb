package com.yongjinbao.utils;

/**
 * Created by fddxiaohui on 2015/8/6.
 */
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtils {

      //Redis服务器IP
//	  private static String ADDR = "10.12.34.1";
//	  private static String ADDR = "139.196.6.9";
    private static String ADDR = "119.23.50.218";

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码 SettingUtils.get().getRedisPwd()"yPK!OxP*nO5Cg%@zO5#9"
    private static String AUTH = "L!lhn$CGLzPiY!1r";

    
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWait(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH);
//            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                //resource.auth(AUTH);
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 释放jedis资源
     */
    public static void returnBrokenResorce(Jedis jedis){
        if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 返还给连接池
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
    
    /** 
     * 获取数据 
     *  
     * @param key 
     * @return 
     */  
    public static String get(String key){  
        String value = null;  
        Jedis jedis = null;  
        try {  
            jedis = getJedis();  
            value = jedis.get(key);  
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return value;  
    }
    
    public static byte[] get(byte[] key){  
    	byte[] value = null;  
        Jedis jedis = null;  
        try {  
            jedis = getJedis(); 
            value = jedis.get(key);
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return value;  
    }
    
    /** 
     * 设置数据 
     *  
     * @param key 
     * @return 
     */  
    public static String set(String key,String value,int expire){
    	String result = null; 
        Jedis jedis = null;  
        try {  
            jedis = getJedis();  
            result = jedis.set(key, value);
            if(expire!=0){
            	jedis.expire(key, expire);
            }
            
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return result;  
    }
    /** 
     * 设置数据 
     *  
     * @param key 
     * @return 
     */
    public static String set(String key,String value){
    	return set(key,value,0);
    }
    /** 
     * 设置数据 
     *  
     * @param key 
     * @return 
     */
    public static String set(byte[] key,byte[] value,int expire){
    	String result = null; 
        Jedis jedis = null;  
        try {  
            jedis = getJedis();  
            result = jedis.set(key, value);
            if(expire!=0){
            	jedis.expire(key, expire);
            }
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return result;  
    }
    /** 
     * 设置数据 
     *  
     * @param key 
     * @return 
     */
    public static String set(byte[] key,byte[] value){
    	return set(key,value,0);
    }
    

    /** 
     * 删除数据 
     *  
     * @param key 
     * @return 
     */  
    public static Long del(String key){
    	Long result = null; 
        Jedis jedis = null;  
        try {  
            jedis = getJedis();  
            result = jedis.del(key);
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return result;  
    }
    
    /** 
     * 删除数据 
     *  
     * @param key 
     * @return 
     */  
    public static Long del(byte[] key){
    	Long result = null; 
        Jedis jedis = null;  
        try {  
            jedis = getJedis();  
            result = jedis.del(key);
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);
        }  
        return result;  
    }
    
    public static void main(String[] args) {
    	set("d".getBytes(), SerializeUtil.serialize("d"));
		byte[] bs = get("d".getBytes());
		String unserialize = (String)SerializeUtil.unserialize(bs);
		System.out.println(unserialize);
    	
    	set("d", "d");
		String d = get("d");
		System.out.println(d);
	}
}