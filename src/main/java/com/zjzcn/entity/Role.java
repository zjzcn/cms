package com.zjzcn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


/**
 * @classDescription 角色类
 * @author zhangjz
 * @version [v1.0.0 2013-05-17]
 */
@Entity
@Table(name = "tb_role")
public class Role extends BaseEntity
{
    private static final long serialVersionUID = 6324412766044932935L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;//角色名称
    
    @Column(name = "is_super")
    private Integer isSuper;//是否为管理员
    
    @Column(name = "remark")
    private String remark;//角色介绍
    
	@ElementCollection
	@CollectionTable(name = "tb_role_perm", joinColumns=@JoinColumn(name="role_id"))
	@Column(name = "perm_url")
    private Set<String> permissions = new HashSet<String>(0);

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

    public Integer getIsSuper()
    {
        return isSuper;
    }

    public void setIsSuper(Integer isSuper)
    {
        this.isSuper = isSuper;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

}