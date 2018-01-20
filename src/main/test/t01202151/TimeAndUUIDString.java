package t01202151;

import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class TimeAndUUIDString {
    @Test
    public void a(){
        Date date = new Date();
        Long la = date.getTime();
        System.out.println(la+"\n"+la.toString().length());
        //13+36=49
    }
}
