package com.gp12713.netty.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    ServerSocket serverSocket;
    public BIOServer(int port) {
        try {
            //Tomcat 默认端口8080
            //只要是Java写的都这么玩，Mysql 3306
            //Oracle 1521
            //Redis  6379
            //Zookeeper  2181
            serverSocket = new ServerSocket(port);
            System.out.println("BIO服务已启动，监听端口："+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen()throws IOException{
        //循环监听
        while(true){
            //等待客户端连接，阻塞方法
            Socket client = serverSocket.accept();
            System.out.println(client.getPort());
            //读数据
            InputStream is=client.getInputStream();
            byte[] buff = new byte[1024];
            int len = is.read(buff);
            if(len>0){
                System.out.println("收到："+new String(buff,0,len));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOServer(8086).listen();
    }
}
