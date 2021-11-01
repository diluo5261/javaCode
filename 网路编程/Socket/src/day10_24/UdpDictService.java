package day10_24;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

public class UdpDictService {
    private DatagramSocket socket;
    private HashMap<String ,String> dict = new HashMap<>();

    public UdpDictService(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        dict.put("hello","你好");
        dict.put("cat","猫");
        dict.put("dog","狗");
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        while(true){
            //1.读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(requestPacket);

            //2.计算请求响应
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            String response = process(request);

            //3.把响应写回到客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            String log = String.format("[%s:%d] req:%s; resp:%s",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
            System.out.println(log);
        }

    }

    private String process(String request){
        return dict.getOrDefault(request,"[单词在词典中不存在!]");
    }

    public static void main(String[] args) throws IOException {


        UdpDictService service = new UdpDictService(9098);
        service.start();
    }
}
