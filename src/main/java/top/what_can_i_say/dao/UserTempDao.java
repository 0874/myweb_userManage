package top.what_can_i_say.dao;

import top.what_can_i_say.model.UserTemp;

public interface UserTempDao {

    /**
     * 生成验证链接，发送邮件，返回验证码
     * */

    public String produceVerifyAndSendEmail(String email);

    /**
     * 查询条目
     * */
    public UserTemp findUserTempByEmail(String email);
    /**
     * 插入一条信息
     * */
    public boolean insertOne(String email,String vc);
    /**
     * 更新数据
     * */

    public boolean updateVerifyCodeByEmail(String email,String vc);
    //设置条目无效
    public void setInvalidByEmail(String email);
}
