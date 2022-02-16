package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpService {

    //创建一个DatagramSocket用于接收和发送数据
    private DatagramSocket socket;

    public UdpService(int port) throws SocketException {
        //创建服务器的DatagramSocket,用有参构造,指定服务器端的端口号
        this.socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("UDP服务器端启动!!");

        while(true){
            //1.创建一个 DatagramSocket 用于接收从客户端发送过来的数据
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());

            //2.处理接收到的请求
            String response = process(request);
            //3.创建datagramPacket,用于存储要发送的数据
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            String log = String.format("[%s : %d] req : %s ; rep : %s",requestPacket.getAddress(),requestPacket.getPort(),request,response);
            System.out.println(log);
        }
    }

    private String process(String request){
        return "这是响应回来的数据"+request;
    }

    public static void main(String[] args) throws IOException {
        UdpService service = new UdpService(9095);
        service.start();
    }
}
