import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SharedStackTest {

    private SharedStack<Integer> sstack1;
    private SharedStack<Integer> sstack2;

    @Before
    public void initSharedStacks(){
        sstack1 = new SharedStack<>();
        sstack2 = new SharedStack<>();
    }
    @After
    public void printSharedStacks(){
        System.out.print(sstack1.toString() + "\n");
        System.out.print(sstack2.toString() + "\n");
    }
    @Test
    public void push() {
        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(2);

        sstack2 = sstack2.push(1);
        sstack2 = sstack2.push(2);

        assertEquals(sstack1.toString(),"//2//1//");
        assertEquals(sstack2.toString(),"//2//1//");
        assertEquals(sstack1,sstack2);
    }
    @Test
    public void pushNull(){
        sstack1 = sstack1.push(null);

        assertEquals(sstack1.toString(), "//null//");
        assertNotEquals(sstack1,sstack2);
    }

    @Test
    public void pop(){
        try{
            sstack1 = sstack1.push(1);
            sstack1 = sstack1.pop();

            assertEquals(sstack1,sstack2);  /*sstack2 is empty, so sstack1 and
                                             * sstack2 should be the same*/
        }catch(StackError err){
            Assert.fail();
        }
    }
    @Test(expected = StackError.class)
    public void popEmptyStack() throws StackError {
            sstack1.pop();
    }
    @Test
    public void popNull(){
        try{
            sstack1 = sstack1.push(null);
            sstack1 = sstack1.pop();
            assertEquals(sstack1,sstack2);
        }catch(StackError e){
            Assert.fail();
        }
    }
    @Test
    public void top(){
        try {
            sstack1 = sstack1.push(1);
            sstack2 = sstack2.push(1);

            assertEquals(sstack1.top(), (Integer) 1);
            assertEquals(sstack1, sstack2);
            assertEquals(sstack1.top(), sstack2.top());
        }catch (StackError e){
            Assert.fail();
        }
    }
    @Test
    public void topNull(){
        try {
            sstack1 = sstack1.push(null);
            sstack2 = sstack2.push(null);

            assertEquals(sstack1.top(), null);
            assertEquals(sstack1,sstack2);
        }catch (StackError e){
            Assert.fail();
        }
    }
    @Test(expected = StackError.class)
    public void topEmptyStack() throws StackError{
        sstack1.top();
    }

    @Test
    public void isEmptyNotStackEmpty() {
        sstack1 = sstack1.push(1);
        assertFalse(sstack1.isEmpty());
    }
    @Test
    public void isEmptyNotEmptyStackNull(){
        sstack1 = sstack1.push(null);

        assertFalse(sstack1.isEmpty());
    }
    @Test
    public void isEmptyEmptyStack() {
        assertTrue(sstack1.isEmpty());
    }
    @Test
    public void mutableTest(){
        SharedStack<Human> humanStack1 = new SharedStack<>();
        SharedStack<Human> humanStack2;

        humanStack1 = humanStack1.push(new Human("H1"));
        humanStack1 = humanStack1.push(new Human("H2"));
        humanStack1 = humanStack1.push(new Human("H3"));

        Human human4 = new Human("H4");

        humanStack2 = humanStack1.push(human4);
        humanStack1 = humanStack1.push(human4);

        assertEquals(humanStack1,humanStack2);

        human4.name = "changedName";

        try{
            assertEquals(humanStack1.top().name,human4.name);
            assertEquals(humanStack2.top().name,human4.name);
            assertEquals(humanStack1,humanStack2);
        }catch(StackError error){
            Assert.fail();
        }


        System.out.print(humanStack1+"\n"+humanStack2+"\n");

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

        assertTrue(sstack1.equals(sstack2));
    }
    @Test
    public void equalsFalsePositive(){

        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(2);

        sstack2 = sstack2.push(2);
        sstack2 = sstack2.push(1);

        assertFalse(sstack1.equals(sstack2));
    }
    @Test
    public void equalsDifferentStackSameTop(){
        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(2);

        sstack2 = sstack2.push(3);
        sstack2 = sstack2.push(2);

        assertFalse(sstack1.equals(sstack2));
    }
    @Test
    public void equalsDifferentLengthSameTop(){
        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(2);
        sstack1 = sstack1.push(3);

        sstack2 = sstack2.push(3);

        assertFalse(sstack1.equals(sstack2));
    }
    @Test
    public void equalsWithNull(){
        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(null);

        sstack2 = sstack2.push(1);
        sstack2 = sstack2.push(null);

        assertTrue(sstack1.equals(sstack2));
    }
    /* toString tests */
    @Test
    public void toStringEmptyStack(){
        assertEquals(sstack1.toString(),"//");
    }
    @Test
    public void toStringNotEmptyStack(){
        sstack1 = sstack1.push(2);
        sstack1 = sstack1.push(3);

        assertEquals(sstack1.toString(),"//3//2//");
    }
    @Test
    public void toStringNull(){
        sstack1 = sstack1.push(1);
        sstack1 = sstack1.push(null);
        sstack1 = sstack1.push(2);
        assertEquals(sstack1.toString(),"//2//null//1//");
    }
}