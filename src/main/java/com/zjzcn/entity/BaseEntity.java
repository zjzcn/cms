package com.zjzcn.entity;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * @author  zhangjz
 * @version  [v1.0.0, 2012-7-8]
 */
public abstract class BaseEntity implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    @Transient
    private String startTime;
    @Transient
    private String endTime;
    
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
}
