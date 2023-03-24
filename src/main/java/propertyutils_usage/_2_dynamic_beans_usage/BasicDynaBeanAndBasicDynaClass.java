package propertyutils_usage._2_dynamic_beans_usage;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import propertyutils_usage._1_basic_property_access.EmployeeBeans;
import java.util.HashMap;

/*BasicDynaBean 和 BasicDynaClass
* 提供了基本的通过 DynaProperty 数组来构建 动态Beans 的途径
* */

public class BasicDynaBeanAndBasicDynaClass
{
    public static void main(String[] args)
    {
        // 1. 先配置好有什么属性

        DynaProperty[] props = new DynaProperty[]{
                new DynaProperty("address", java.util.Map.class),
                new DynaProperty("subordinate", EmployeeBeans[].class),
                new DynaProperty("firstName", String.class),
                new DynaProperty("lastName", String.class)
        };

        // 2. 用配置好的动态属性

        // 中间的dynaBeanClass参数用于提交dynaBeanClass接口的实现类，如果为null， 默认传递 Class (大Class类)
        BasicDynaClass dynaclass = new BasicDynaClass("employee", null, props);

        EmployeeBeans kaiven = new EmployeeBeans();
        kaiven.setFirstName("Howson");
        kaiven.setLastName("kaiven");
        EmployeeBeans jack = new EmployeeBeans();
        jack.setFirstName("Howson");
        jack.setLastName("jack");
        EmployeeBeans suboridnates[] = {kaiven, jack};

        String home = "芜湖市XXXX号";
        String workplace = "上海市AAAA号";
        HashMap<String, String> address = new HashMap<>();
        address.put("home", home);
        address.put("workplace", workplace);

        // 3. 创建 DynaBeans 的实例

        DynaBean employee = null;
        try
        {
            employee = dynaclass.newInstance();

        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        } catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }

        // 4. 向DynaBeans中设置信息

        employee.set("address", address); // 这是 Mapped Property
        employee.set("subordinate", suboridnates); // 这是 index property
        employee.set("firstName", "fred"); // 这是 Simple Property
        employee.set("lastName", "Flintstone");

        // 5. 使用DynaBeas中的信息

            // 使用 SimpleProperty
        System.out.println("姓    名: " + employee.get("firstName") + " · " + employee.get("lastName"));
        System.out.print("下属  有: ");
            // 使用 IndexProperty
        System.out.print(((EmployeeBeans)employee.get("subordinate", 0)).getLastName() + "  ");
        System.out.print(((EmployeeBeans)employee.get("subordinate", 1)).getLastName() + "\n");
            // 使用Mapped Property
        System.out.println("家庭地址: " + employee.get("address", "home"));
        System.out.println("工作地址: " + employee.get("address", "workplace"));
    }
}
