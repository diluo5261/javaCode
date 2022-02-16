package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private Socket socket;
    private String serverIp;
    private int serverPort;

    public TcpClient(String serverIp, int serverPort) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = new Socket(serverIp,serverPort);
    }

    public void start(){
        Scanner scan = new Scanner(System.in);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){
            while (true){
                //1.从键盘读取输入的数据
                System.out.println("请输入数据(exit退出)");
                String request = scan.nextLine();

                if ("exit".equalsIgnoreCase(request)){
                    System.out.println("客户端退出!");
                    return;
                }

                //2.把这个读取的数据构造成请求,发给服务器端
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(request);
                writer.flush();

                //3.接收从服务器端响应回的数据
                Scanner responseScan = new Scanner(inputStream);
                String response = responseScan.nextLine();

                //4.显示结果到页面
                String log = String.format("req : %s;resp : %s",request,response);
                System.out.println(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpClient client = new TcpClient("127.0.0.1", 9090);
        client.start();

    }
}
