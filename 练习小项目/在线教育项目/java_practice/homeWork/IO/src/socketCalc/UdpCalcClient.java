package socketCalc;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpCalcClient {
    private String serverId;
    private int serverPort;
    private DatagramSocket socket;

    public UdpCalcClient(String serverId, int serverPort) throws SocketException {
        this.serverId = serverId;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void start() throws IOException {
        System.out.println("客户端启动成功!!");
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("输入运算符:");
            String symbol = scan.next();
            System.out.println("输入第一个数字");
            int num1 = scan.nextInt();
            System.out.println("输入第二个数字");
            int num2 = scan.nextInt();



            String request = num1+";"+symbol+";"+num2;
            //创建DatagramPacket存储请求得数据
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serverId),serverPort);
            socket.send(requestPacket);


            DatagramPacket responsePacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0, responsePacket.getLength());


            System.out.println("result:"+response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpCalcClient client = new UdpCalcClient("127.0.0.1", 8888);
        client.start();
    }
}
