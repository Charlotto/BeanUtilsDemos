package propertyutils_usage._1_basic_property_access;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class AccessPropery
{
    public static void main(String[] args)
    {
        EmployeeBeans employee1 = new EmployeeBeans();
        EmployeeBeans subordinate1 = new EmployeeBeans();
        EmployeeBeans subordinate2 = new EmployeeBeans();

        try
        {
            /**
             * 访问 `简单属性`
             */
            // 设置
            PropertyUtils.setSimpleProperty(employee1, "firstName", "三");
            PropertyUtils.setProperty(employee1, "lastName", "张");

            PropertyUtils.setSimpleProperty(subordinate1, "lastName", "王");
            PropertyUtils.setProperty(subordinate1, "firstName", "五");

            PropertyUtils.setProperty(subordinate2, "lastName", "李");
            PropertyUtils.setProperty(subordinate2, "firstName", "四");

            // 读取
            String firstname = (String) PropertyUtils.getSimpleProperty(employee1, "firstName");
            String lastname = (String) PropertyUtils.getProperty(employee1, "lastName");
            System.out.println("Employee name:" + lastname + " " + firstname);

            /**
             * 访问  `有序属性`
             */
            // 设置
            PropertyUtils.setIndexedProperty(employee1, "subordinate[0]" ,subordinate1);
            PropertyUtils.setIndexedProperty(employee1, "subordinate", 0, subordinate2);

            // 读取
            EmployeeBeans s1 = (EmployeeBeans) PropertyUtils.getIndexedProperty(employee1, "subordinate[0]");
            EmployeeBeans s2 = (EmployeeBeans) PropertyUtils.getIndexedProperty(employee1, "subordinate", 1);
            System.out.println("[1] " + s1);
            System.out.println("[2] " + s2);

            /**
             * 访问 `键值属性`
             * 与前面两种有不同，属性的键通过小括号包括，例如访问address属性中的 home 键值，应该表示为 address(home)
             */
            String address = "江西省新余市";
            String worksapce = "江西省南昌市";
            // 设置
            PropertyUtils.setMappedProperty(subordinate1, "Address(home)", address);
            PropertyUtils.setMappedProperty(subordinate1, "Address", "workspace", worksapce);

            // 读取
            String homeAddress = (String) PropertyUtils.getMappedProperty(subordinate1, "Address(home)"); // 属性与键合并
            String worksapceAddress = (String) PropertyUtils.getMappedProperty(subordinate1, "Address", "workspace"); // 属性与键分离
            System.out.println("home addr: " + homeAddress);
            System.out.println("workspace addr: " + worksapceAddress);


            /**
             * 访问 `内嵌属性`
             * 属性值本身是个对象,但其类定义不能被内嵌
             */
            AddressDetail ad = new AddressDetail();
            ad.setCity("南昌");
            ad.setProvince("江西");
            // 设置
            PropertyUtils.setNestedProperty(employee1, "addressDetail", ad);
            // 访问
            String city = (String) PropertyUtils.getNestedProperty(employee1, "addressDetail.city");
            System.out.println("employee1 city of home: " + city);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }

    }
}
