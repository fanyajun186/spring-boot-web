package com.neo.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Crawler {
	
    public static void main(String[] args) throws Exception {   	
    	
        String url = "http://www.wubupua.com/html/7203.html";        
        String elementType="img";
        String keyWord="upload";
        
        getData(url,elementType,keyWord);
    }

    
	private static void getData(String url, String elementType, String keyWord) throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, "UTF-8");

        Document document = Jsoup.parse(html);
        
        Elements elements = document.select(elementType);

        int nameIndex = 1;
        for (Element element : elements) {
            String img_url = element.attr("src");
            if (img_url.indexOf(keyWord) > 0) {
                URL img_Url = new URL(img_url);
                URLConnection connection = img_Url.openConnection();
                InputStream inputStream = connection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(new File("f:\\img", String.valueOf(nameIndex)) + ".jpg");
                byte[] buf = new byte[1024];
                int l;
                while ((l = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, l);
                }
                outputStream.close();
                inputStream.close();
                System.out.println("已经将第" + nameIndex + "张图片下载到了本地");
                nameIndex++;
                Thread.sleep(10);
            }
        }
        System.out.println("所有图片下载完成");
		
	}
    
    
}