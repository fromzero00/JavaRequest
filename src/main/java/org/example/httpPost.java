package org.example;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

public class httpPost {
    public static final String url = "http://cms.duoceshi.cn/cms/manage/loginJump.do";
    public static final String account = "admin";
    public static final String password = "123456";
//    public static final String form = "userAccount=admin&loginPwd=123456";
//    @Test
    public static String login(String account,String password) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> jsonbody = new ArrayList<NameValuePair>();
        jsonbody.add(new BasicNameValuePair("userAccount", account));
        jsonbody.add(new BasicNameValuePair("loginPwd", password));
        UrlEncodedFormEntity urlformat = new UrlEncodedFormEntity(jsonbody, "utf-8");
        httpPost.setEntity(urlformat);
        HttpResponse response;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String reslut = EntityUtils.toString(entity);
            System.out.println(reslut);
            String cookies = response.getFirstHeader("Set-Cookie").toString();
            String cookies01 = StringUtils.substringBefore(cookies,";");  //分割字符串
            String token = StringUtils.substringAfter(cookies01," ");
//            System.out.println(token);
            EntityUtils.consume(entity);
            httpclient.close();
            return token;
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
//    @Test
    public static void queryuserinfo() throws Exception{
        String url = "http://cms.duoceshi.cn/cms/manage/queryUserList.do";
        String token = login(account,password);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Cookie",token);
        httpPost.addHeader("Content-type","application/x-www-from-urlencoded");
        System.out.println(token);
        ArrayList<NameValuePair> jsonbody = new ArrayList<NameValuePair>();
        jsonbody.add(new BasicNameValuePair("startCreateDate",""));
        jsonbody.add(new BasicNameValuePair("endCreateDate",""));
        jsonbody.add(new BasicNameValuePair("searchValue","'ikun'"));
        jsonbody.add(new BasicNameValuePair("page","1"));
        UrlEncodedFormEntity requestbody = new UrlEncodedFormEntity(jsonbody, "utf-8");
        httpPost.setEntity(requestbody);
        HttpResponse response;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println(result);
//            System.out.println(123);
//            EntityUtils.consume(entity);
//            httpclient.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * form表单格式
     * @param args
     * @throws Exception
     */

//    public static void main(String[] args) throws Exception {
////        httpPost post = new httpPost();
////        post.login("admin","123456");
////        post.queryuserinfo();
////        queryuserinfo();
//        login("admin","123456");
//        queryuserinfo();
//    }
    }

