package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpService {
    private ServerSocket listenSocket;

    public TcpService(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("TCP服务器端启动成功!!!");

        while(true){
            Socket clientSocket = listenSocket.accept();
            processConnections(clientSocket);
        }

    }

    private void processConnections(Socket clientSocket) throws IOException {
        //处理一个连接,再这个连接中可能会涉及到客户端和服务器端之间的多次交互
        String log= String.format("[%s %d] 客户端上线了!",clientSocket.getInetAddress().toString(),clientSocket.getPort());

        System.out.println(log);

        try(InputStream inputStream = clientSocket.getInputStream(); OutputStream outputStream = clientSocket.getOutputStream()) {
            while(true){
                Scanner scan = new Scanner(inputStream);

                if (!scan.hasNext()){
                    log = String.format("[%s %d]客户端下线!!",clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    return;
                }

                //2.根据请求处理响应
                String request = scan.next();
                String response = process(request);

                //3.将数据响应惠客户端
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();

                log = String.format("[%s %d] : req : %s ; resp : %s",clientSocket.getInetAddress().toString(),clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
                当前的clientSocket声明周期,不是跟随整个程序,而是和连接相关
                因此就需要每个连接结束,都要进行关闭,否则随着连接的增多,这个socket就会出现内存泄漏的问题
             */
            clientSocket.close();
        }
    }

    private String process(String request) {
        return "这是响应回来的数据 : "+request;
    }

    public static void main(String[] args) throws IOException {
        TcpService service = new TcpService(9090);
        service.start();
    }
}
