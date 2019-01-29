package org.netty.study.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zpp
 * @date 2019/1/29 11:43
 */
public class TimeServer {

    public static void main(String[] args) throws Exception{
        int port = 8080;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("开启端口监听客户端连接：" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                //TODO 启动一个线程去处理客户端的连接
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        }


    }
}
