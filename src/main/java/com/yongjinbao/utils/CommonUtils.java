package com.yongjinbao.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public final class CommonUtils {

	public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
	
	public static String generateSMSCaptcha(){
		String a = "0123456789";
		StringBuffer codeBuffer = new StringBuffer();
		for (int i = 0; i < 4; i++){ 
			int rand = (int) (Math.random() * a.length()); 
			codeBuffer.append(a.charAt(rand));
		} 
		return codeBuffer.toString();
	}
	
	public static boolean sendSMS(String mobile,int templateType,String param){
		String url="http://115.28.50.135:8888/sms.aspx";
		HttpPost request=new HttpPost(url);
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "send"));
		params.add(new BasicNameValuePair("userid", "1367"));
		params.add(new BasicNameValuePair("account", "房多多"));
		params.add(new BasicNameValuePair("password", "juren000"));
		
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("content", "【大公盘】您的验证码是："+param+"，有效时间30分钟。"));
		HttpEntity httpEntity;
		try {
			httpEntity = new UrlEncodedFormEntity(params,"utf-8");
			request.setEntity(httpEntity);;
			HttpClient httpClient=new DefaultHttpClient();
			HttpResponse response=httpClient.execute(request);
			String result=EntityUtils.toString(response.getEntity());
			System.out.println(result);
			((Closeable) httpClient).close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	public static String httpRequestGet(String url){
		 	//url="http://agent.esf.fangdd.com/agent/18676360180/isExist";
			HttpGet request = new HttpGet(url);
			try {
				HttpClient httpClient=new DefaultHttpClient();
				HttpResponse response=httpClient.execute(request);
				String result=EntityUtils.toString(response.getEntity());
				((Closeable) httpClient).close();
				return result;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(httpRequestGet("http://agent.esf.fangdd.com/agent/18676360180/isExist"));

	}
	

}