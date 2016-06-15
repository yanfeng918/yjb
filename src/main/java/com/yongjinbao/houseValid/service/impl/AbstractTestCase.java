package com.yongjinbao.houseValid.service.impl;//package com.yongjinbao.houseinfo.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fangdd.core.exception.ErrorCode;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.thrift.protocol.TJSONProtocol;
//import org.apache.thrift.protocol.TProtocol;
//import org.apache.thrift.transport.TMemoryBuffer;
//import org.junit.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URLEncoder;
//import java.util.*;
//
///**
// * 单元测试基类.
// *
// * @author xiezhengrong
// * @since 1.0.0
// */
//public abstract class AbstractTestCase {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    protected static final String SUCCESS_CODE = ErrorCode.success_0.getError().code();
//
//    private String host = "10.0.5.60";
////    private String host = "localhost";;
//    private String port;
//    private String version = "v1.0.0";
//
//    /**
//     * JUnit初始化时执行
//     */
//    @Before
//    public void setUp() throws Exception {
//        Properties properties = new Properties();
//        InputStream in = getClass().getResourceAsStream("/server.properties");
//        properties.load(in);
//        in.close();
//        port = properties.getProperty("server.http.port");
//        logger.debug("初始化调试环境：{host=" + host + ", port=" + port + "}");
//    }
//
//    /**
//     * 使用Get发起Http+Thrift请求
//     *
//     * @param api
//     * @param request
//     * @param returnClass
//     * @return 返回字符串
//     * @throws Exception
//     */
//    public String thriftGetString(String api, Object request, Class<?> returnClass) {
//        Object obj;
//        try {
//            obj = thriftGet(api, request, returnClass);
//            return JSONObject.toJSONString(obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 使用Get发起Http+Thrift请求
//     *
//     * @param api
//     * @param request
//     * @param returnClass
//     * @return Thrift实例，即returnClass的实例
//     * @throws Exception
//     */
//    public <T> T thriftGet(String api, Object request, Class<T> returnClass) throws Exception {
//        String content = null;
//        if (request != null) {
//            content = convertToString(request);
//        }
//
//        // 调用HTTPClient
//        String apiUrl = renderApiFullUrl(api);
//
//        return get(apiUrl, content, returnClass);
//    }
//
//    /**
//     * 将Thrift实例转换成字符串
//     *
//     * @param request
//     * @return
//     */
//    private String convertToString(Object request) {
//        if (request != null) {
//            logger.debug("请求参数：" + JSONObject.toJSONString(request));
//        }
//        TMemoryBuffer buffer = new TMemoryBuffer(1024);
//        TJSONProtocol protocol = new TJSONProtocol(buffer);
//        try {
//            org.apache.commons.lang.reflect.MethodUtils.invokeMethod(request, "write", protocol);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = Arrays.copyOf(buffer.getArray(), buffer.length());
//        String content = new String(bytes);
//        return content;
//    }
//
//    /**
//     * 使用POST发起Http+Thrift请求
//     *
//     * @param api
//     * @param request
//     * @param returnClass
//     * @return 返回响应Thrift的JSON字符串
//     */
//    public String thriftPostString(String api, Object request, Class<?> returnClass) {
//        Object obj = thriftPost(api, request, returnClass);
//        return JSONObject.toJSONString(obj);
//    }
//
//    /**
//     * 使用POST发起Http+Thrift请求
//     *
//     * @param api
//     * @param request
//     * @param clazz
//     *            Thrift的对象类
//     * @return 返回class对应的实例对象
//     */
//    public <T> T thriftPost(String api, Object request, Class<T> clazz) {
//        String content = null;
//        if (request != null) {
//            content = convertToString(request);
//        }
//
//        // 调用HTTPClient
//        String apiUrl = renderApiFullUrl(api);
//
//        return post(apiUrl, content, clazz);
//    }
//
//    private String renderApiFullUrl(String api) {
//        return "http://" + host + ":" + port + "/" + version + api;
//    }
//
//    private <T> T post(String url, final String data, Class<T> clazz) {
//        Map<String, String> params = new HashMap<String, String>();
//        if (data != null) {
//            params.put("data", data);
//        }
//        return post(url, params, clazz);
//    }
//
//    private <T> T post(String url, Map<String, String> params, Class<T> clazz) {
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        // 创建httppost
//        HttpPost httppost = new HttpPost(url);
//
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        if (params != null && params.size() > 0) {
//            logger.debug("请求参数：");
//            Set<String> ks = params.keySet();
//            for (String k : ks) {
//                String v = params.get(k);
//                logger.debug("\t" + k + "=" + v);
//                formparams.add(new BasicNameValuePair(k, v));
//            }
//        }
//
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httppost.setEntity(uefEntity);
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                return getThriftResponse(response, clazz);
//            } finally {
//                response.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    private <T> T get(String url, final String data, Class<T> clazz) throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        if (data != null) {
//            params.put("data", data);
//        }
//        return get(url, params, clazz);
//    }
//
//    /**
//     * 发送 get请求
//     *
//     * @return
//     * @throws Exception
//     */
//    private <T> T get(String url, Map<String, String> params, Class<T> clazz) throws Exception {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            if (params.size() > 0) {
//                if (!url.contains("?")) {
//                    url += "?";
//                }
//                logger.debug("请求参数：");
//                Set<String> ks = params.keySet();
//                for (String k : ks) {
//                    String v = params.get(k);
//                    logger.debug("\t" + k + "=" + v);
//                    url += k + "=" + URLEncoder.encode(v, "utf-8") + "&";
//                }
//            }
//            if (url.endsWith("&")) {
//                url = url.substring(0, url.length() - 1);
//            }
//            logger.debug("发送GET请求：" + url);
//
//            // 创建httpget.
//            HttpGet httpget = new HttpGet(url);
//
//            // 执行get请求.
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            try {
//                // 获取响应实体
//                return getThriftResponse(response, clazz);
//            } finally {
//                response.close();
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private <T> T getThriftResponse(CloseableHttpResponse response, Class<T> clazz) throws Exception {
//        int statusCode = response.getStatusLine().getStatusCode();
//        HttpEntity entity = response.getEntity();
//        if (statusCode != HttpStatus.SC_OK) {
//            InputStream input = entity.getContent();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(input));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = in.readLine()) != null) {
//                buffer.append(line);
//            }
//            throw new Exception(buffer.toString());
//        }
//        if (entity != null) {
//            // 打印响应内容
//            InputStream input = entity.getContent();
//            BufferedReader inputBody = new BufferedReader(new InputStreamReader(input));
//            StringBuffer body = new StringBuffer();
//            String line = "";
//            while ((line = inputBody.readLine()) != null) {
//                body.append(line);
//            }
//
//            TMemoryBuffer buffer = new TMemoryBuffer(1024);
//
//            buffer.write(body.toString().getBytes());
//            TJSONProtocol proto = new TJSONProtocol(buffer);
//            input.close();
//
//            T thrift = clazz.newInstance();
//            Method method = thrift.getClass().getMethod("read", TProtocol.class);
//            method.invoke(thrift, proto);
//
//            logger.debug("接口调用返回的结果: {}", JSON.toJSONString(thrift));
//            return thrift;
//        } else {
//            throw new Exception("响应内容为空！");
//        }
//    }
//
//    /**
//     * 设置调用的服务地址信息.
//     *
//     * @param host
//     * @param port
//     */
//    public void setHostInfo(String host, String port) {
//        logger.debug("设置调试环境host为:{}, port:{}", host, port);
//        this.host = host;
//        this.port = port;
//    }
//
//    /**
//     * 设置调用的服务地址信息.
//     *
//     * @param host
//     * @param port
//     */
//    public void setHostInfo(String host, String port, String version) {
//        logger.debug("设置调试环境host为:{}, port:{}, version:{}", host, port, version);
//        this.host = host;
//        this.port = port;
//        this.version = version;
//    }
//}
