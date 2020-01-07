package com.fuqiang.springbootmail.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 生成六位短信验证码
 *
 *
 * @author fuqiang
 * @date 2019/4/28 15:34
 */
@Slf4j
public class VerifyCodeGenerator {

    //    生成验证码
    public static String getVerifyCode()
    {
        Random random=new Random();
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<6;i++)
        {
            stringBuffer.append(String.valueOf(random.nextInt(10)));
        }
        return  stringBuffer.toString();
    }

}
