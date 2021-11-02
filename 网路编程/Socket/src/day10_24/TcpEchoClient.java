package day10_24;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket socket;
    private String serverIp;
    private int serverPort;


    public TcpEchoClient(String serverIp, int serverPort) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        //创建 socket的同时,就和服务器建立连接
        socket = new Socket(serverIp,serverPort);
    }

    public void start(){
        Scanner scan = new Scanner(System.in);

        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()){
            while(true){
                //1.从键盘,读取用户输入的内容
                System.out.println("请输入数据:(exit退出)");
                String request = scan.next();

                if ("exit".equalsIgnoreCase(request)){
                    return;
                }
                //2.把这个读取的内容构造成请求,发送给服务器
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(request);
                writer.flush();

                //3.从服务器获取响应并解析
                Scanner responseScan = new Scanner(inputStream);
                String response = responseScan.next();
                //4.把结果显示到页面上
                String log = String.format("req : %s; resp : %s",request,response);
                System.out.println(log);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient("127.0.0.1", 9090);
        tcpEchoClient.start();
    }


}
