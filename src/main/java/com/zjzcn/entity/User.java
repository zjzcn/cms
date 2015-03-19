package com.zjzcn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author zhangjz
 * @version [v1.0.0 2013-05-17]
 */
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity
{
    private static final long serialVersionUID = -635145904278724337L;

    // Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
    @Column(name="name")
    private String name;//用户名
	
	@Column(name="username")
	private String username;//登陆名
	
	@Column(name="password")
	private String password;//密码
	
    @Column(name="gender")
    private Integer gender;//性别：0为女，1为男
	
	@Column(name="email")
	private String email;//email
	
    @Column(name="mobile")
    private String mobile;//mobile
	
    @Column(name="create_time")
    private String createTime;//创建时间
    
    @Column(name="is_disabled")
    private Integer isDisabled;//用户状态  0：可用  1：禁用 

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY)
    @JoinTable(name="tb_user_role",inverseJoinColumns={@JoinColumn(name="role_id")},joinColumns={@JoinColumn(name="user_id")})
    private Set<Role> roles=new HashSet<Role>(0);
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getGender()
    {
        return gender;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDisabled()
    {
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled)
    {
        this.isDisabled = isDisabled;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }
    
}