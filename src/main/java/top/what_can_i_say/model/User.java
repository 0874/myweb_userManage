package top.what_can_i_say.model;

public class User {
	//自增长
	private Integer id;
	//称呼非唯一，有默认值
	private String name;
	//密码非唯一，有默认值
	private String password;
	//电子邮箱唯一，
	private String email;
	//登录标识【时间加上UUID值】
	private String loginID;
	/**
	 * 不同的构造函数对应不同的情况
	 * */
	
	public User(String email) {
		super();
		this.email = email;
		name="默认名字-请修改";
		password = "123456";
		id=null;
	}
	public User(Integer id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 显示对象信息
	 * */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}
	/**
	 * 快捷生成getter,setter
	 * */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
}
