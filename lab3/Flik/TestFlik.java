import org.junit.Test;

import static org.junit.Assert.*;

public class TestFlik {
    @Test
    public void test(){
        int A = 128;
        int B = 128;
        boolean exp = true;
        assertEquals(exp,Flik.isSameNumber(A, B));
    }
}
