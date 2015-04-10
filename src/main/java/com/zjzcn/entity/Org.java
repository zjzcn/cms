package com.zjzcn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 部门实体类，继承抽象安全实体类
 * 
 * @author yuqs
 * @since 0.1
 */
@Entity
@Table(name = "tb_org")
public class Org extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	// 根部门ID号默认为0
	public static final Long ROOT_ORG_ID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	// 上级部门
	@ManyToOne
	@JoinColumn(name="pid", nullable=true)
	private Org parentOrg;
	// 部门编码
	@Column(name="code")
	private String code;
	// 部门名称
	@Column(name="name")
	private String name;
	// 部门描述
	@Column(name="description")
	private String description;
	// 部门管辖的所有用户列表（一对多关联）
	@OneToMany(mappedBy = "org",cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<User>();
	// 部门管辖的所有下级部门列表（一对多关联）
	@OneToMany(mappedBy = "parentOrg",cascade = CascadeType.ALL)
	private List<Org> childrenOrgs = new ArrayList<Org>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Org getParentOrg() {
		return parentOrg;
	}
	public void setParentOrg(Org parentOrg) {
		this.parentOrg = parentOrg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Org> getChildrenOrgs() {
		return childrenOrgs;
	}
	public void setChildrenOrgs(List<Org> childrenOrgs) {
		this.childrenOrgs = childrenOrgs;
	}
	
}
