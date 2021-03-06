package zhy2002.leetcode.tests;

import zhy2002.leetcode.solutions.minstack.MinStack;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class MinStackTests {

    @Test
    public void basicTest() {
        MinStack stack = new MinStack();
        stack.push(5);
        Assert.assertEquals(5, stack.getMin());

        stack.push(9);
        Assert.assertEquals(5, stack.getMin());

        stack.push(1);
        Assert.assertEquals(1, stack.getMin());

        stack.push(3);
        Assert.assertEquals(1, stack.getMin());

        stack.pop();
        Assert.assertEquals(1, stack.getMin());

        stack.pop();
        Assert.assertEquals(5, stack.getMin());

        stack.pop();
        Assert.assertEquals(5, stack.getMin());

        stack.pop();
        try{
            stack.getMin();
            assertTrue(false);
        }catch (Throwable ex){
            assertTrue(ex instanceof  IndexOutOfBoundsException);
        }

    }

    @Test
    public void duplicateTest(){
        MinStack stack = new MinStack();
        stack.push(1);
        stack.push(1);

        assertThat(stack.getMin(), equalTo(1));

        stack.pop();
        assertThat(stack.getMin(), equalTo(1));
    }
}
