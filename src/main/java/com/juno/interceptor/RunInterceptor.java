package com.juno.interceptor;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class RunInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before: invocationRUN=[" + invocation + "]");
        System.out.println(Arrays.toString(invocation.getArguments()));
        Object rval = invocation.proceed();
        System.out.println("Invocation returned");
        return rval;
    }

}
