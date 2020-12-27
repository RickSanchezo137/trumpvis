package team.noname.trumpvis.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Java2Python {

    public static String doGet(String url) {
        String res = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            HttpGet httpGet = new HttpGet(url);
            res = httpClient.execute(httpGet, responseHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static String doPost(String text, String url) {
        String res = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            HttpPost httpPost = new HttpPost(url);
            //给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(text,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //第四步：发送HttpPost请求，获取返回值
            res = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
