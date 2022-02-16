package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class UdPDictService {
    private DatagramSocket socket;
    private Map<String , String> dict= new HashMap<>();;

    public UdPDictService(int port) throws SocketException {
        //service端,创建DatagramSocket,使用带参数的构造器,指定服务器端的端口号
        this.socket = new DatagramSocket(port);
        dict.put("hello","你好");
        dict.put("cat","猫");
        dict.put("dog","狗");
        dict.put("bird","鸟");
    }

    public void start() throws IOException {
        System.out.println("service启动成功!!!");
        while(true){

            //1.创建 DatagramPacket,用于接收client 发送的请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());

            //2.计算请求响应
            String response = process(request);

            //3.将数据写回到客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            String log = String.format("[%s : %d] req:%s ;resp : %s",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response);
            System.out.println(log);

        }
    }

    private String process(String request) {
        return dict.getOrDefault(request,"字典中没有这个单词!");
    }

    public static void main(String[] args) throws IOException {
        UdPDictService service = new UdPDictService(8888);
        service.start();
    }


}
