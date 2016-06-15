package com.yongjinbao;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;




public class HttpClentTest {


	public void httpRemote() {
		String url = "http://10.12.34.11/yjb/common/area";
		String status = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		CloseableHttpResponse response = null;
		try {  
			HttpGet httpget = new HttpGet(url);  
			// 执行get请求.
			//System.out.print(new Date());
			response = httpclient.execute(httpget);  
			// 获取响应实体    
			HttpEntity entity = response.getEntity();  
			// 打印响应状态    
			if (entity != null) {  
				//获取响应内容    
				status = EntityUtils.toString(entity);
			}  
		} catch (ClientProtocolException e) {  
			e.printStackTrace();  
		}  catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			// 关闭连接,释放资源    
			try {  
				httpclient.close(); 
			} catch (IOException e) {  
				System.out.println("httpClient错误\n"+"远程错误："+url);
			} 
			try {
				response.close();  
			} catch (Exception e) {
				System.out.println("response错误\n"+"远程错误："+url);
			}
		}
		System.out.println(status);
	}
	
	@Test
	public void test(){
		for (int i = 0; i < 200; i++) {
			httpRemote();
		}
	}
	
	
	
	private static HttpPost postForm(String url, Map<String, String> params){  
        
        HttpPost httpost = new HttpPost(url);  
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
          
        try {  
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return httpost;  
    }
	
	@Test
	public void test01() throws ParseException, IOException{
		String url="http://dagongpan.cn/yjb/login/submit";
		HttpPost request=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "yanfeng"));
		params.add(new BasicNameValuePair("password", "123123"));
		HttpEntity httpEntity=new UrlEncodedFormEntity(params,"utf-8");
		request.setEntity(httpEntity);
		HttpClient httpClient=new DefaultHttpClient();
		HttpResponse response=httpClient.execute(request);
		String result=EntityUtils.toString(response.getEntity());
		System.out.println(result);
		((Closeable) httpClient).close();
	}
	
	@Test
	public void test02() throws ParseException, IOException{
		String url="http://115.28.50.135:8888/sms.aspx";
		HttpPost request=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "send"));
		params.add(new BasicNameValuePair("userid", "1367"));
		params.add(new BasicNameValuePair("account", "房多多"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("mobile", "13122045162"));
		params.add(new BasicNameValuePair("content", "【凌讯中科】您的注册验证码为：1065，请妥善保管。"));
		HttpEntity httpEntity=new UrlEncodedFormEntity(params,"utf-8");
		request.setEntity(httpEntity);
		HttpClient httpClient=new DefaultHttpClient();
		HttpResponse response=httpClient.execute(request);
		String result=EntityUtils.toString(response.getEntity());
		System.out.println(result);
		((Closeable) httpClient).close();
	}
	
	

}
