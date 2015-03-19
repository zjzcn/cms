package com.zjzcn.util.query.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjz
 * @version  [v1.0.0, 2012-7-8]
 */
public class WhereCondition implements QueryCondition
{
    private String propertyName;    
    private Object value1;   
    private Object value2;    
    private Op op;
    
    private List<Object> paramList=new ArrayList<Object>();
    
    public WhereCondition(String propertyName, Op op)
    {
        this(propertyName,null,null, op);
    }
    
    public WhereCondition(Object value, Op op)
    {
        this(null,value,null, op);
    }
    
    public WhereCondition(Object value1, Object value2, Op op)
    {
        this(null,value1,value2, op);
    }
    
    public WhereCondition(String propertyName, Object value, Op op)
    {
        this(propertyName,value,null, op);
    }
    
    public WhereCondition(String propertyName, Object value1, Object value2, Op op)
    {
        this.propertyName = propertyName;
        this.value1 = value1;
        this.value2 = value2;
        this.op = op;
    }
    
    public String toQueryString()
    {
        StringBuilder hqlBuf = new StringBuilder();
        
        switch (op)
        {
            case EQ:
            {
                hqlBuf.append(propertyName).append("=?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case NE:
            {
                hqlBuf.append(propertyName).append("<>?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case LIKE:
            {
                hqlBuf.append(propertyName).append(" like ?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case LIKE_START:
            {
                hqlBuf.append(propertyName).append(" like ?" + paramList.size());
                paramList.add(value1+"%");
                break;
            }
            case LIKE_END:
            {
                hqlBuf.append(propertyName).append(" like ?" + paramList.size());
                paramList.add("%"+value1);
                break;
            }
            case LIKE_ANYWHERE:
            {
                hqlBuf.append(propertyName).append(" like ?" + paramList.size());
                paramList.add("%"+value1+"%");
                break;
            }
            case GT:
            {
                hqlBuf.append(propertyName).append(">?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case LT:
            {
                hqlBuf.append(propertyName).append("<?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case LE:
            {
                hqlBuf.append(propertyName).append("<=?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case GE:
            {
                hqlBuf.append(propertyName).append(">=?" + paramList.size());
                paramList.add(value1);
                break;
            }
            case BETWEEN:
            {
                hqlBuf.append(propertyName).append(" between ?" + paramList.size() + " and ?" + (paramList.size()+1));
                paramList.add(value1);
                paramList.add(value2);
                break;
            }
            case IN:
            {
                Object[] values = (Object[])value1;
                int len = values.length;
                
                StringBuilder buf = new StringBuilder(len * 12);
                for (int i = 0; i < len - 1; i++)
                {
                    buf.append("?" + paramList.size() + ", ");
                    paramList.add(values[i]);
                }
                buf.append("?" + paramList.size());
                paramList.add(values[len - 1]);
                
                hqlBuf.append(propertyName).append(" in (").append(buf).append(')');
                break;
            }
            case NOT_IN:
            {
                Object[] values = (Object[])value1;
                int len = values.length;
                
                StringBuffer buf = new StringBuffer(len * 12);
                for (int i = 0; i < len - 1; i++)
                {
                    buf.append("?" + paramList.size() + ", ");
                    paramList.add(values[i]);
                }
                buf.append("?" + paramList.size());
                paramList.add(values[len - 1]);
                
                hqlBuf.append(propertyName).append(" not in (").append(buf).append(')');
                break;
            }
            case IS_NULL:
            {
                hqlBuf.append(propertyName).append(" is null");
                break;
            }
            case IS_NOT_NULL:
            {
                hqlBuf.append(propertyName).append(" is not null");
                break;    
            }
            default:
        }
        return hqlBuf.toString();
    }
    
    public List<Object> getParamList()
    {
        return paramList;
    }


    public enum Op
    {
        EQ,
        NE,
        LIKE,
        LIKE_START,
        LIKE_END,
        LIKE_ANYWHERE,
        GT,
        LT,
        LE,
        GE,
        BETWEEN,
        IN,
        NOT_IN,
        IS_NULL,
        IS_NOT_NULL,
    }

}
