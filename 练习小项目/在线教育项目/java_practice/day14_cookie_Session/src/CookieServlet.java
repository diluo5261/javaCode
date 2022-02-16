import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       createCookie(req,resp);
        getCookie(req, resp);





    }

    /**
     * 从服务器中获取Cookie
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            resp.getWriter().write("Cookie["+cookie.getName()+"="+cookie.getValue());
        }

    }



    /**
     * Cookie创建
     * @param req
     * @param resp
     */
    private void createCookie(HttpServletRequest req, HttpServletResponse resp){
        //1.创建Cookie对象
        Cookie cookie = new Cookie("key1","value1");

        //2. 通知客户端保存cookie
        resp.addCookie(cookie);

        // 可以创建多个cookie
        Cookie cookie1 = new Cookie("key2","value2");
        resp.addCookie(cookie1);
    }


}
