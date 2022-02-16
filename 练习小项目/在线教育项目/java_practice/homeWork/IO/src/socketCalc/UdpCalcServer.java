package socketCalc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpCalcServer {
    private DatagramSocket socket;

    public UdpCalcServer(int port) throws SocketException {
        //服务器端创建DatagramSocket,指定服务器端的端口
        this.socket = new DatagramSocket(port);
    }

    public void start() throws IOException {

        System.out.println("服务器端启动成功!");
        while (true){
            //1.创建 DatagramPacket用于存储客户端发送过来的数据
            DatagramPacket requestPacket = new DatagramPacket(new byte[1024],1024 );
            socket.receive(requestPacket);

            String request = new String(requestPacket.getData(),0, requestPacket.getLength());
            String response = process(request);


            //创建 DatagramPacket 用于存储发响应给客户端的数据
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            String log = String.format("[ %s %d] req : %s ; resp : %s",requestPacket.getAddress(),requestPacket.getPort(),request,response);
            System.out.println(log);

        }
    }

    private String process(String request) {
        String[] strings = request.split(";");

        if (strings.length != 3){
            return "数据格式传输错误";
        }
        int num1 = Integer.parseInt(strings[0]);
        int num2 = Integer.parseInt(strings[2]);

        String res = "计算结果";

        switch (strings[1]){
            case "+":
                res = num1+num2+"";
                break;
            case "-":
                res = num1-num2+"";
                break;
            case "*":
                res = num1*num2+"";
                break;
            case "/":
                res = num1/num2+"";
                break;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        UdpCalcServer server = new UdpCalcServer(8888);
        server.start();
    }
}
