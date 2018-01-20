package top.what_can_i_say.model;


/**
 * 注册过程：用户提供email,把email和验证码存入临时数据库，服务器把验证链接发送到用户邮箱，用户可点击邮箱链接完成注册
 * 临时邮箱：条目有效时长不限【直到用户注册成功后设置为无效】，定期清理过期条目
 * */
public class UserTemp {
    //与业务逻辑无关的主键
    private  Integer id;
    //用户邮箱
    private String email;
    //如果表中已经存在该邮箱，但用户多次请求注册，则更改这里的验证码，
    private  String verifyCode;
    //该条目是否已经成功注册
    private boolean valid = true;//在mysql里配置默认值和hiberntae-mapping里配置了都为0，不好使

    /**
     * 创建表user_temp:::
     * Create Table: CREATE TABLE `user_temp` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `email` varchar(45) NOT NULL,
     `verify_code` varchar(45) NOT NULL,
     `valid` tinyint(4) NOT NULL DEFAULT '0',
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8
     * */


    /**
     * 构造函数
     * */
    public UserTemp() {

    }

    public UserTemp(String email, String verifyCode, boolean valid) {
        this.email = email;
        this.verifyCode = verifyCode;
        this.valid = valid;
    }

    public UserTemp(String email, String verifyCode) {
        this.email = email;
        this.verifyCode = verifyCode;
    }

    /**
     * getter/setter
     * */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
