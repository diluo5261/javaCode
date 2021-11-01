package day10_21;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpEchoServer {
    private DatagramSocket socket = null;

    /*
    port表示端口号
    服务器启动的时候,需要关联(绑定)一个端口号
    收到数据的时候,就会根据这个端口号来决定把这个数据交给哪个进程
    虽然此处port 写的类型是 int 型,但是实际上端口号是一个两个字节的无符号整数
    范围是 0 ~ 65535
     */

    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    //通过这个方法来启动服务器
    public void start() throws IOException {
        System.out.println("服务器启动!");

        //服务器一般的就是持续运行的(7*24)
        while(true){
            /*
            1.读取请求,当前服务器不知道客户端啥时候发来请求,receive方法也会阻塞
            如果有请求过来,此时receive就会返回
            参数DatagramPacket是一个输出型参数,socket中读到的数据会设置到这个参数的对象中
            DatagramPacket 在构造的时候,需要指定一个缓冲区(就是一段内存空间,通常使用byte[] 来表示)
             */
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);

            //把requestPacket对象里面的内容取出来,作为一个字符串
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());
            //2.根据请求来计算相应

            String response = process(request);

            //3.把响应写回到客户端,这是也需要构造一个DatagramPacket
            //此处给的 DatagramPacket 中设置的长度,必须是 "字节的个数
            //如果直接取 response.length() 此处得到的是,字符串的长度,也就是字符的个数
            //当前的responsePacket 在构造的时候,还需要指定这个包要发给谁
            //其实发送给的目标.就是发请求的那一方
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            //4.加上日志打印
            //不建议使用字符串拼接
            String log = String.format("[%s : %d] req : %s ; resp : %s",
                                        requestPacket.getAddress().toString(),
                    requestPacket.getPort(),
                    request,response);

            System.out.println(log);
        }
    }

    //此处的 process 方法负责功能,就是根据请求来计算响应
    //由于当前是一个回显服务器,就把客户端发的请求直接返回回去即可
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9090);
        server.start();
    }
}
