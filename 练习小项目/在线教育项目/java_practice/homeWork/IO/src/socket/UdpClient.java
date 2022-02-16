package socket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpClient {
    //创建两个属性,用于指定服务器端的ip地址和端口号
    private String serviceIp;
    private int servicePort;
    private DatagramSocket socket;

    public UdpClient(String serviceIp, int servicePort) throws SocketException {
        this.serviceIp = serviceIp;
        this.servicePort = servicePort;
        //客户端使用无参的构造方法,用于接收和发送数据
        this.socket = new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("UDP客户端启动成功!!!");
        while(true){
            System.out.println("请输入数据:(exit退出)");
            String request = scan.nextLine();

            if ("exit".equalsIgnoreCase(request)){
                return;
            }

            //创建DatagramPacket 用于存储发送的数据
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(serviceIp),servicePort);
            socket.send(requestPacket);

            //创建一个DatagramPacket 用于存储从服务器端接收的数据
            DatagramPacket responsePacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0, responsePacket.getLength());

            String log = String.format("req : %s ; resp : %s",request,response);
            System.out.println(log);

        }
    }

    public static void main(String[] args) throws IOException {
        UdpClient client = new UdpClient("127.0.0.1", 9095);
        client.start();
    }
}
