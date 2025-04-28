package org.example.timeOut;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TimeOutTest {

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testAvecTimeOut() {
        try {
            sleep(499);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Timeout(1)
    void testAvecTimeOutSecond() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testEchoueTimeOut() {
        Assertions.assertTimeout(Duration.ofMillis(600),
                () -> Thread.sleep(500), "Le test doit être inférieur à 600");
    }

}
