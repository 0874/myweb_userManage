package top.what_can_i_say.util;

import top.what_can_i_say.dao.UserDao;
import top.what_can_i_say.dao.UserDao201801201300;
import top.what_can_i_say.dao.UserTempDao;
import top.what_can_i_say.dao.UserTempDaoImpl201801201418;

public class MySingletonObject {
    //操作user的DB类
    private final static UserDao userDao= new UserDao201801201300();
    //操作user_temp的
    private  final  static UserTempDao userTempDao = new UserTempDaoImpl201801201418();

    public static UserTempDao getUserTempDao() {
        return userTempDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
