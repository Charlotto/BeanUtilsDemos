package propertyutils_usage._1_basic_property_access;

import java.util.HashMap;
import java.util.LinkedList;

public class EmployeeBeans
{

    private HashMap<String, String> address;
    private LinkedList<EmployeeBeans> subordinate;
    private String firstName;
    private String lastname;

    private AddressDetail addressDetail;

    public EmployeeBeans()
    {
        address = new HashMap<String, String>();
        subordinate = new LinkedList<EmployeeBeans>();
    }

    /**
     * 为了让 PropertyUtils 动态地调用属性的 设置器 和 访问器，他们两个名字必须与属性名字相对应
     * 如： 属性为 firstname 或 firstName 则设置器和访问器名必须为 setFirstName 和 getFirstName, 这样才能通过 “firstname” 访问此属性
     */

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastname;
    }

    public void setLastName(String lastName)
    {
        this.lastname = lastName;
    }

    public String getAddress(String type)
    {
        return address.get(type);
    }

    public void setAddress(String type, String value)
    {
        address.put(type, value);
    }

    public EmployeeBeans getSubordinate(int index)
    {
        return subordinate.get(index);
    }

    public void setSubordinate(int index, EmployeeBeans subordinate)
    {
        this.subordinate.add(index, subordinate);
    }


    @Override
    public String toString()
    {
        return "EmployeeBeans{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastname + '\'' +
                '}';
    }

    public AddressDetail getAddressDetail()
    {
        return addressDetail;
    }

    public void setAddressDetail(AddressDetail addressDetail)
    {
        this.addressDetail = addressDetail;
    }
}
