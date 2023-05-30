package com.spring;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.Arrays;

public class WebServer {
    private ToyContainer ctx;
    public WebServer(ToyContainer ctx) {
        this.ctx = ctx;
    }

    public void run() throws Exception {
        int port = 3000;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream os = clientSocket.getOutputStream();

            String s;
            String filename = "";

            while ((s = in.readLine()) != null) { // localhost:3000 을 bufferedReader 로 호출 했을 때
                if ( s.contains("GET /")) { // -> GET 이면
                    System.out.println("--> " + s); // List 호출
                    String path = s.split(" ")[1];
                    if ( path.equals("/") )  filename = "/index.html";
                    else filename = path;
                }
                if (s.isEmpty()) break;
            }

            try {
                // web server
                if (filename.indexOf('.') >= 0) {
                    String header = "HTTP/1.0 200 OK\r\n" +
                            URLConnection.getFileNameMap().getContentTypeFor(filename) + "\r\n" +
                            "\r\n";
                    os.write(header.toString().getBytes());
                    File file = new File("." + filename);
                    FileInputStream input = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int readData;
                    while ((readData = input.read(buffer)) > 0) {
                        os.write(buffer, 0, readData);
                    }
                    input.close();
                } else {
                    // spring container
                    String header = "HTTP/1.0 200 OK\nContent-Type: text/html\r\n\r\n";
                    String body = ctx.request(filename);
                    os.write(header.toString().getBytes());
                    os.write(body.toString().getBytes());
                }

            } catch (Exception e) {
                e.printStackTrace();
                String header = "HTTP/1.0 500 ERROR\r\nContent-Type: text/html\r\n\n";
                String body = "<html><h3><p>Error : " + filename + ": " + e.toString() + "</p>" + Arrays.toString(e.getStackTrace()) + "</h3></html>";
                os.write(header.toString().getBytes());
                os.write(body.toString().getBytes());
            } finally {
                os.flush();
                os.close();
                in.close();
                clientSocket.close();
            }
        }
    }
}