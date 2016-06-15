package com.yongjinbao.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RandomCode {

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的key
    private Random random = new Random();
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
    
    private int width = 80;//图片宽
    private int height = 26;//图片高
    private int lineSize = 40;//干扰线数量
    private int stringNum = 4;//随机产生字符数量

    /*
     * 获取随机的字符
     */
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
    
    public static void main(String[] args) {
		System.out.println(new RandomCode().getRandomString(6));;
	}
    
}