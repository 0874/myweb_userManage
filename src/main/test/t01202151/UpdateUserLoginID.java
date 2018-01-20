package t01202151;

import org.junit.Test;
import top.what_can_i_say.dao.UserDao201801201300;

public class UpdateUserLoginID {
    @Test
    public void as(){
        new UserDao201801201300().updateUserLoginID("e12s");
    }
}
