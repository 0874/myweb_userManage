package top.what_can_i_say.dao;

import top.what_can_i_say.model.User;

/**
 * 我的网站只有一个数据库，名字【what_can_i_say】
 * 另外，还有外键的问题，本来我想将来有多个应用，是不是还要再新建其他的数据库，但用户账号是系列共享的，那是不是得有数据库之间的外键映射，
 * 而看到知乎上说外键只是在设计业务时考虑，当具体定义到sql时则不用，考虑到性能原因；【https://www.zhihu.com/question/19600081】
 * 这么说的话，我就只用一个数据库了，并且也不定义外键，业务约束通过中间层控制,
 * 
 * */
public interface UserDao {
	/**
	 * 添加新用户
	 * */
	public boolean addOneUserByEmail(String email);

	/**
	 *
	 * 查询用户
	 *
	 * **/
	public User findUserByEmail(String email);
	//查询登录标识
	public String findUserLoginIDByEmail(String email);

	/**
	 * 更新用户登录标识
	 * */
	public String updateUserLoginID(String email);

}
/**
 * 创建表user的语句:
 * CREATE TABLE `what_can_i_say`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '默认名字-请修改',
  `password` VARCHAR(45) NOT NULL DEFAULT '123456',
  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

 * */
