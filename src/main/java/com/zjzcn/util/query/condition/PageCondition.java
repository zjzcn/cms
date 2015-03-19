package com.zjzcn.util.query.condition;

import com.zjzcn.util.query.PageBean;

/**
 * @author zhangjz
 * @version  [v1.0.0, 2012-7-8]
 */
@SuppressWarnings("rawtypes")
public class PageCondition implements QueryCondition
{
    private Integer pageNo;
    private Integer pageSize;
    private PageBean pageBean;
    
    public PageCondition(Integer pageNo, Integer pageSize)
    {
        this.pageNo=pageNo;
        this.pageSize=pageSize;
    }

	public PageCondition(PageBean pageBean)
    {
        this.pageBean=pageBean;
    }
    
    public int getPageNo()
    {
        return pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public String toQueryString()
    {
    	if(pageBean!=null)
    	{
    		return (pageBean.getOrderProperty() + " " + pageBean.getOrderDirection());
    	}
        return "";
    }

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
    
}
