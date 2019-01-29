package org.netty.study.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author zpp
 * @date 2019/1/29 11:50
 */
public class TimeServerHandler implements Runnable {


    private Socket socket;

    TimeServerHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String body = null;
            String msg = null;
            while (true){
                body = br.readLine();
                if (body == null) {
                    break;
                }
                msg = "query time".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "Bye";
                out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.socket = null;
            }
        }

    }
}
