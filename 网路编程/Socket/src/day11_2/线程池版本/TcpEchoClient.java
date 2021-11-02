package day11_2.线程池版本;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket clientSocket;
    private String serverId;
    private int serverPort;

    public TcpEchoClient(String serverId, int serverPort) throws IOException {
        this.serverId = serverId;
        this.serverPort = serverPort;
        this.clientSocket = new Socket(serverId,serverPort);
    }

    public void start(){
        Scanner scan = new Scanner(System.in);
        System.out.println("TCP客户端启动成功!!");

        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()){

            while(true){
                System.out.println("请输入数据:(exit退出)");
                String request = scan.nextLine();

                if ("exit".equalsIgnoreCase(request)){
                    System.out.println("程序退出!!");
                    return;
                }

                //将数据写入到服务器端
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(request);
                writer.flush();
                Scanner responseScan = new Scanner(inputStream);
                String response = responseScan.nextLine();

                String log = String.format("req : %s ; resp: %s",request,response);
                System.out.println(log);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1", 8888);
        client.start();
    }
}
