package com.yongjinbao.houseNew.service.impl;//package com.yongjinbao.houseinfo.service.impl;
//
//public class RemoteAgentCheck {
//
//	/**
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
//}
//
//
