package propertyutils_usage._2_dynamic_beans_usage;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;

public class CustomDynaBeanTemplate implements DynaBean
{
    @Override
    public boolean contains(String s, String s1)
    {
        return false;
    }

    @Override
    public Object get(String s)
    {
        return null;
    }

    @Override
    public Object get(String s, int i)
    {
        return null;
    }

    @Override
    public Object get(String s, String s1)
    {
        return null;
    }

    @Override
    public DynaClass getDynaClass()
    {
        return null;
    }

    @Override
    public void remove(String s, String s1)
    {

    }

    @Override
    public void set(String s, Object o)
    {

    }

    @Override
    public void set(String s, int i, Object o)
    {

    }

    @Override
    public void set(String s, String s1, Object o)
    {

    }
}
