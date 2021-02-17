package org.miro.widget.unit;

import org.junit.jupiter.api.Test;
import org.miro.widget.WidgetApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WidgetApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @Test
    public void mainMethodTest() throws NoSuchMethodException, SecurityException {
        String methodName = "main";
        Class<?> c = WidgetApplication.class;
        Method method = c.getDeclaredMethod(methodName, String[].class);
        assertNotNull(method);
    }
}
