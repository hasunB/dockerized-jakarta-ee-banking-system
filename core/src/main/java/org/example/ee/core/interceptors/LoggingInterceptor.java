package org.example.ee.core.interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.example.ee.core.annotation.Logged;

import java.util.Arrays;

@Interceptor
@Logged
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        long start = System.currentTimeMillis();

        String className = ctx.getTarget().getClass().getSimpleName();
        String methodName = ctx.getMethod().getName();
        Object[] params = ctx.getParameters();

        System.out.println("[LOG] " + className + "." + methodName + " called with params: " + Arrays.toString(params));

        try {
            Object result = ctx.proceed();
            long duration = System.currentTimeMillis() - start;

            System.out.println("[LOG] " + className + "." + methodName + " executed in " + duration + " ms");

            return result;
        } catch (Exception e) {
            System.err.println("[LOG] Exception in " + className + "." + methodName + ": " + e.getMessage());
            throw e;
        }
    }
}

