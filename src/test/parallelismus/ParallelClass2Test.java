package src.test.parallelismus;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelClass2Test {

    @Test
    public void parallel6() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel7() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel8() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel9() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }

    @Test
    public void parallel10() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(true);
    }
}
