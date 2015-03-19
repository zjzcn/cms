package com.zjzcn.util.query.condition;

/**
 * @author zhangjz
 * @version  [v1.0.0, 2012-7-8]
 */
public class OrderCondition implements QueryCondition
{
    private boolean ascending;
    private String propertyName;
    
    public OrderCondition(String propertyName, boolean ascending)
    {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    public String toQueryString()
    {
        return propertyName + " " + (ascending ? "asc" : "desc");
    }
}
