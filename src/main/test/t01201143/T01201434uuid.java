package t01201143;

import org.junit.Test;

import java.util.UUID;

public class T01201434uuid {
    @Test
    public void t1(){
        String a  = UUID.randomUUID().toString();
        System.out.println(a);
        System.out.println(a.length());
    }
}
