package propertyutils_usage._2_dynamic_beans_usage;

import com.zplus.dbutils.DruidUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;

/** WrapDynaBean 和 WrapDynaClass 简介
 *  提供将标准 JavaBeans 转换为 DynaBeans 的途径，以实现让 javaBean 使用 BeanUtils API 的可能
 */
public class WrapDynaBeanAndWrapDynaClass
{
    public static void main(String[] args)
    {
        TestUserBean tb = new TestUserBean(1, "testUser");// 普通 JavaBean
        DynaBean wrapper = new WrapDynaBean(tb); // 将 标准JavaBean转化为 DynaBean
        System.out.println(wrapper.get("id")); // 使用 DynaBean API 访问其中字段
    }
}

class TestUserBean
{
    int id;
    String name;

    public TestUserBean(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}