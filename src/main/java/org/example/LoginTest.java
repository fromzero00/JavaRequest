package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class LoginTest extends AbstractJavaSamplerClient {
//    public Arguments getDefaultParameters(){
//        Arguments arguments = new Arguments();
//        //添加Jmeter界面的参数（参数名，默认值或说明）
//        arguments.addArgument("account","");
//        arguments.addArgument("passwd","");
//        arguments.addArgument("url","");
//        return arguments;
//    }
    public SampleResult runTest(JavaSamplerContext arg) {
        //定义一个变量name，用来储存用户传递过来的account参数。
        //这里的参数要与上面方法的值对应，account、passwd、url
        //业务里参数写死的话，可以不用
//        String name = arg.getParameter("account");
//        String pwd = arg.getParameter("passwd");
//        String url = arg.getParameter("url");
        SampleResult result = new SampleResult();
        //表示开始计时
        result.sampleStart();
        //以下为业务的请求
        String token;
        try {
              //定义请求的数据
              result.setSamplerData("123");
              token= httpPost.login("admins","1234567");
              result.setResponseData(token,SampleResult.TEXT);
              result.setSuccessful(true);
              result.setResponseCode("200");
              result.setResponseMessage("success");
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setResponseCode("500");
            result.setResponseData(e.getMessage(),SampleResult.TEXT);
        }
        //定义结果，用于查看接过树

        //定义结果，用于查看接过书的状态
        //结束计时
        result.sampleEnd();

        return result;

    }
}
