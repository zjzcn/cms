package com.zjzcn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author zhangjz
 * @version [v1.0.0 2013-05-17]
 */
@Entity
@Table(name = "tb_log")
public class Log extends BaseEntity
{
    private static final long serialVersionUID = -635145904278724337L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;// 用户名
	
	@Column(name="logType")
	private Integer logType;//0:操作日志；1：异常日志
	
	@Column(name="ip")
    private String ip;
	
	@Column(name="create_time")
	private String createTime;
	
	@Column(name="name")
	private String name;
	
	@Column(name="content")
	private String content;
	
	public Long getId()
	{
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}