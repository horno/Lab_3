import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SharedStackTest {

    private SharedStack<Integer> sstack1; //canviar nom
    private SharedStack<Integer> sstack2;

    @Before
    public void beforeTests(){
        sstack1 = new SharedStack<>();
        sstack2 = new SharedStack<>();
    }

    @Test
    public void push() {
        assertEquals(true,sstack1.equals(sstack2));

    }

    @Test
    public void pop() {
    }

    @Test
    public void top() {
    }

    @Test
    public void isEmpty() {

    }

    /* toString and equals implementation tests */

    /* equals tests */
    @Test
    public void equalsTest(){
        sstack1 = sstack1.push(10);
        sstack1 = sstack1.push(20);
        sstack1 = sstack1.push(30);

        sstack2 = sstack2.push(10);
        sstack2 = sstack2.push(20);
        sstack2 = sstack2.push(30);

        assertEquals(true,sstack1.equals(sstack2));
    }
    @Test
    public void equalsFalsePositive(){

        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(2);

        sstack2 = sstack2.push(2);
        sstack2 = sstack2.push(1);

        assertEquals(false,sstack1.equals(sstack2));
    }
    @Test
    public void equalsWithNull(){
        sstack1 = sstack1.push(1);
        sstack1 = sstack2.push(null);

        sstack2 = sstack2.push(1);
        sstack2 = sstack2.push(null);

        assertEquals(true,sstack1.equals(sstack2));

    }
    /* toString tests */

}