package net;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpDemo {
//    public static  final String URL_PREFIX = "https://tx-safety-video.acfun.cn/mediacloud/acfun/acfun_video/";
    public static final String Header_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    public static void main(String[] args) throws IOException {
        //1.初始化URL
//        String URL_PARAM = "20e8bb52e9f49262-a240d48de8cd978893d68bfacfcb6a38-hls_720p_hevc_1.m3u8?pkey=ABCeaDHxgqbWlyZqovmerUv14t52adhAPFUevPaJJ_iJB9OAC3y6FDx_OrCwkqvCHaj8mbG3AEjr4WzHs2ChcJVwOxTU_7OE1akeiHqptJNGEzW4oRFuW9tXj51-YZOAxejgMhvkOS0p9UKbR5vXlWK4g_wVb8jy465jjsXRpWKNoYq7rQi4z9u5BLz4JczNchpkT5tygf9gbR6_lqx5vHTAGsH3NIrwzgW6qtBbhSMQ0MofFPaORgwwkPg-aAz_pMU&safety_id=AAJ5aBNgGOxh66dhJnmR-fWH";
        URL url = new URL("https://ali-safety-video.acfun.cn/mediacloud/acfun/acfun_video/ebb3ad71bcd18079-ce8b71f542a7b47b51f9b26fc11f389c-hls_720p_hevc_1.m3u8?pkey=ABD_nB_Y3v1rE_CI2dSq77O2QCW1h-7GPVvJUHMdzfkjDRuVdHo_tdslATjqd7QgFmf-bqm_b4m9szmFxv0bFVOW6m9vpfichnj5KdPXVFQrIji191YRa4I3cSaCQDb0PenAh7I55QdLJR5w91gsVfepEPt2scoqzInasoe1unQkE736-a5kBXOlvHl0aRiJfuCSQfuOMSR66vCedJ37crcGRJ3sDJ1BBU8bFGSpMZowmln5MJVzAgy-x2ndfHVgTAc&safety_id=AAJ5aBNgGOxh66dhJnmR-fWH\n");

        //2.设置HTTP请求参数,获取m3u8文件
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", Header_UA);
        httpURLConnection.setRequestProperty("Referer","https://www.acfun.cn/");



        //3.输出返回信息
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //3.2优化,引入多线程
        ExecutorService executorService = Executors.newFixedThreadPool(9);

        for(int i = 0; i < 9; i++) {
            executorService.execute(new task());
        }

        //4.解析获取文件,输出获取到的url,使用Reg expression匹配String
        String b;
        while((b = bufferedReader.readLine()) !=null) {
            if(b.startsWith("ebb3ad71bcd18079-ce8b71f542a7b47b51f9b26fc11f389c-hls_720p_hevc")){
                System.out.println(b);
            }
        }

        //5.关闭流和连接
        inputStream.close();
        httpURLConnection.disconnect();

    }

    static class task implements Runnable {
        public void download(URL url) throws IOException {
            File file = new File(url.toString());
            FileWriter fileWriter = new FileWriter(file);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行完成");
        }
    }
}
