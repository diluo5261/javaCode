package day10_24;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpDictClient {
    private String serviceId;
    private int servicePort;
    private DatagramSocket socket;

    public UdpDictClient(String serviceId, int servicePort) throws SocketException {
        this.socket = new DatagramSocket();
        this.serviceId = serviceId;
        this.servicePort = servicePort;
    }

    public void start() throws IOException {
        System.out.println("客户端启动成功!");
        Scanner scan = new Scanner(System.in);
        while(true){
            //读取输入的数据
            System.out.println("输入数据:(exit退出)");
            String request = scan.nextLine();

            if ("exit".equalsIgnoreCase(request)){
                return;
            }

            //构造请求,并发送给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serviceId),servicePort);

            socket.send(requestPacket);


            //从服务器中获取数据
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(),0, responsePacket.getLength());
            System.out.println(String.format("req : %s; resp : %s",request,response));
        }
    }

    public static void main(String[] args) throws IOException {
        UdpDictClient udpDictClient = new UdpDictClient("127.0.0.1", 9098);
        udpDictClient.start();
    }
}
