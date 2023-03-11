package basic_usage;

import basic_usage.beans.Info;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;


/** 中文乱码解决方案:
 *
 */
@WebServlet(name = "GetSomeInfo", value = "/get-some-info")
public class GetSomeInfo extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        req.setCharacterEncoding("UTF-8"); // 加在这里只对通过 get请求 传递的中文数据有效
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8"); // 在post中指定 req传入的数据使用UTF-8编码格式
        resp.setContentType("text/html;charset=utf-8"); // 设定响应为一个页面,字符集指定为UTF-8, 默认是 ISO-8859-1 不支持中文

        Info info = new Info();
        try
        {
            BeanUtils.populate(info, req.getParameterMap());
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println(info);
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + info + "</h1>"); // 如果 req没有设置字符集为 UTF-8, 则此行中文输出乱码
        out.println("<h1>" + "你好" + "</h1>"); // 如果 setContentType没有指定字符集 charset=UTF-8 则此行中文输出乱码
    }
}
