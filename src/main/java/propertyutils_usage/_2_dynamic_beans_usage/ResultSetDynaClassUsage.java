package propertyutils_usage._2_dynamic_beans_usage;

import com.zplus.dbutils.DruidUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** ResultSetDynaClass 说明
 *  顾名思义，将ResultSet中的内容直接封装为DynaClass的类
 */
public class ResultSetDynaClassUsage
{
    static
    {
        DruidUtils.init(ResultSetDynaClassUsage.class.getResourceAsStream("/druid.properties"));
    }
    public static void main(String[] args) throws SQLException
    {
        //1. 获取 Connection
        Connection conn = DruidUtils.getConnection(false);
        PreparedStatement pstmt = conn.prepareStatement("select * from user;");
        ResultSet rs = pstmt.executeQuery();

        //2. 获取对象集合迭代器，并使用
        Iterator rows = (new ResultSetDynaClass(rs)).iterator();

        while(rows.hasNext())
        {
            // 将RsultSet中的内容封装为 DynaBean
            DynaBean row = (DynaBean) rows.next();
            System.out.println("id:"+row.get("id") + " name:" + row.get("name"));

        }
        //4. 回收资源
        DruidUtils.close(conn);
        rs.close();
        pstmt.close();
    }
}
