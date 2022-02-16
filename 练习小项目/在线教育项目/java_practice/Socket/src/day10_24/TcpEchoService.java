package day10_24;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoService {
    private ServerSocket listenSocket;

    public TcpEchoService(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public  void start() throws IOException {
        System.out.println("服务器启动成功!");
        while(true){
        /*
        * UDP的服务器进入主循环,就直接尝试 receive 读取请求了
        * 但是 TCP 是有连接的,先需要做的是,建立连接
        * 当服务器运行的时候,当前是否有客户端建立连接,不确定
        * 如果客户端没有建立连接,accept 就会阻塞等待
        * 如果客户端建立连接了,此时 accept 就会返回一个 Socket 对象
        * 进一步的服务器和客户端之间的交互,就交给 clientSocket 来完成
        */
            Socket clientSocket = listenSocket.accept();
            processConnection(clientSocket);
        }
    }


    private void processConnection(Socket clientSocket){

        //处理一个连接,在这个连接中可能会涉及到客户端和服务器之间的多次交互
        String log = String.format("[%s %d]客户端上线了",clientSocket.getInetAddress().toString(),clientSocket.getPort());
        System.out.println(log);
        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {

            while(true){
                //    1.读取请求并解析
                //可以通过 inputStream 的read 把数据读到一个 byte[] ,然后再转成一个String
                //    但是比较麻烦,可以结束Scanner来完成这个工作
                Scanner scan = new Scanner(inputStream);

                if(!scan.hasNext()){
                    log = String.format("[%s: %d]客户端下线!",clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    break;
                }

                /*byte[] buffer = new byte[1024];
                int len;
                while((len = inputStream.read(buffer)) != -1){
                    String str = new String(buffer,0,len);
                    System.out.print(str);
                }*/

                String request = scan.next();
                //2.根据请i去计算响应
                String response = process(request);

                //3.把响应写回给客户端
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();
                log = String.format("[%s : %d] req : %s ;resp : %s",clientSocket.getInetAddress().toString(),clientSocket.getPort(),request,response);
                System.out.println(log);

            }

        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            //当前的clientSocket声明周期,不是跟随整个程序,而是和连接相关.
            //因此就需要每个连接结束,都要进行关闭,否则随着连接的增多,这个socket就会出现内存泄漏的问题
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String process(String request) {
        return "响应的数据"+request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoService tcpEchoService = new TcpEchoService(8888);
        tcpEchoService.start();
    }
}
