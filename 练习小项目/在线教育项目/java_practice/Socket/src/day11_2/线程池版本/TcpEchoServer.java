package day11_2.线程池版本;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {
    private ServerSocket listenSocket;
    private Map<String,String> dict;

    public TcpEchoServer(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
        dict = new HashMap<>();

        dict.put("hello","你好");
        dict.put("cat","猫");
        dict.put("dog","狗");

    }

    public void start() throws IOException {
        System.out.println("Tcp服务器端启动成功!!!");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){

            //在这个代码中,通过线程,就能保证accept 调用完毕之后,就能立刻再次调用 accept
            Socket clientSocket = listenSocket.accept();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            });
        }
    }

    private void processConnection(Socket clientSocket){
        System.out.println(String.format("[%s %s]客户端上线了",clientSocket.getInetAddress().toString(),clientSocket.getPort()));

        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()){

            Scanner scan = new Scanner(inputStream);
            //1.获取客户端发送的数据
            while(true){

                if(!scan.hasNext()){
                    System.out.println(String.format("[%s %s]客户端下线了",clientSocket.getInetAddress().toString(),clientSocket.getPort()));
                    return;
                }

                String request = scan.nextLine();;
                //执行处理过程
                String response = process(request);

                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();

                System.out.println(String.format("[%s : %d] req : %s resp : %s",clientSocket.getInetAddress().toString(),clientSocket.getPort(),request,response));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket!= null && clientSocket.isClosed()){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private String process(String request) {
        return dict.getOrDefault(request,"字典中没有这个字!!");
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(8888);
        server.start();
    }
}
