package com.zcwfeng.componentlibs.surport.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zcw on 2017/2/15.
 */

public class DNSUtils {

    private static void AnalyseDomain(String host){
        String IPAddress = "";
        InetAddress returnStr = null;
        try {
            returnStr = java.net.InetAddress.getByName(host);
            IPAddress = returnStr.getHostAddress();
            System.out.println(IPAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            //未知主机，域名解析失败
        }
        //域名解析成功
    }

    public static void main(String[] args) {
        AnalyseDomain("sp.hls.yinyuetai.com");
    }
}
