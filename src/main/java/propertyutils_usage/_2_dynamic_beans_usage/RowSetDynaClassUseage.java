package propertyutils_usage._2_dynamic_beans_usage;

import com.zplus.dbutils.DruidUtils;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.RowSetDynaClass;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * 展示RowSetDynaClass的使用方式，需要链接数据库
 *
 * RowSetDynaClass 是BeanUtils中将 ResultSet中的内容快速封装为DynaBeans 的工具类，它可以使得ResultSet中的属性不需要在执行SQL后暴露在
 * 程序员面前，同时它解决了ResultSet脱离定义作用域后失效的问题，也就是说即使在 ResultSet 失效以后也能对它的数据进行操作，简化了在把 ResultSet 封装为 JavaBean 之前对数据处理的过程
 */
public class RowSetDynaClassUseage
{
    public static void main(String[] args) throws SQLException
    {
        // 初始化 数据库连接处
        DruidUtils.init(RowSetDynaClassUseage.class.getResourceAsStream("/druid.properties"));
        Connection conn = DruidUtils.getConnection(false);

        // 执行DQL 语句，拿到 ResultSet 并实例化RowSetDynaClass
        String sql = "select * from userInfo;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        /////////////////////////////////////////////
        RowSetDynaClass rsdc = new RowSetDynaClass(rs);
        ////////////////////////////////////////////
        rs.close(); // 一旦RowSetDynaClass 完成传递，则不需要原生 resultSet了
        pstmt.close();
        DruidUtils.close(conn);

        List rows = rsdc.getRows();
        Iterator iter = rows.iterator();
        try
        {
            while (iter.hasNext())
            {
                Object name = PropertyUtils.getProperty(iter.next(), "name");
                System.out.println(name);
            }
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }
}
