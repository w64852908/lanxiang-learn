package annotationinterceptor.interceptor;

import annotationinterceptor.annotation.SystemOut;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by lanxiang on 16/9/18.
 */
public class SystemOutInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SystemOutInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("Execute method " + methodInvocation.getMethod().getName() + ", at " + System.nanoTime());
        return methodInvocation.proceed();
    }
}