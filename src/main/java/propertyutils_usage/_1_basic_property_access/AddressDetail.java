package propertyutils_usage._1_basic_property_access;

/**
 * 此类用于展示Nested Proprety 类该如何写
 */
public class AddressDetail
{
    private String city;
    private String province;


    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }
}
