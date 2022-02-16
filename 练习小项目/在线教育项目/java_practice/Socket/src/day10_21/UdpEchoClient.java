package day10_21;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpEchoClient {

    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    //要连接服务器的ip和端口号
    //客户端在构造这个Socket对象的时候,不需要指定端口号,尤其是不能把serverPort指定进去
    //DatagramSocket构造方法中传入的 port ,这是只定自己的端口
    //如果当前DatagramSocket 构造方法中没有指定端口的话,就相当于操作系统会分配一个空闲的端口号,来给客户端使用
    //客户端的端口号是随机生成的,服务器端的端口号是指定的
    public UdpEchoClient( String serverIp, int serverPort) throws SocketException {
        this.socket = new DatagramSocket();
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        Scanner scan= new Scanner(System.in);

        while(true){
            //1.从标准输入读入一个数据
            System.out.println("->");
            String request = scan.nextLine();

            if (request.equals("goodboy")){
                System.out.println("goodboy");
                return;
            }

            //2.把这个字符串构造成一个 UDP请求
            //这个 DatagramPacket 中,既要包含具体的数据,又要包含这个数据发给谁
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length, InetAddress.getByName(serverIp), serverPort);
            socket.send(requestPacket);

            //从服务器中读取响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);

            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0, responsePacket.getLength());

            //4.显示这个结果
            String log = String.format("req %s; resp: %s\n",request,response);
            System.out.println(log);

        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient("127.0.0.1", 9090);
        client.start();
    }
}
