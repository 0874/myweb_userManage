package t01201143;

import org.junit.Test;
import top.what_can_i_say.util.MyEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class T1450email {
    @Test
    public void f(){
        try {
            MyEmail.sendRegisterLinkbyQQ1147057783("java_is_so_easy@outlook.com","aaa");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String []a){
//        new T1450email().f();
        try {
            MyEmail.sendRegisterLinkbyQQ1147057783("java_is_so_easy@outlook.com","asd");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
