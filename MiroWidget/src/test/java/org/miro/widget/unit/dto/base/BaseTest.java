package org.miro.widget.unit.dto.base;

import org.junit.jupiter.api.Test;
import org.miro.widget.dto.base.Base;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseTest {
    @Test
    public void baseMethods() throws NoSuchMethodException, SecurityException {
        Class<?> c = Base.class;
        String methodName = "hashCode";
        Method method = c.getDeclaredMethod(methodName);
        assertNotNull(method);

        methodName = "equals";
        method = c.getDeclaredMethod(methodName, Object.class);
        assertNotNull(method);

        methodName = "toString";
        method = c.getDeclaredMethod(methodName);
        assertNotNull(method);
    }
}
