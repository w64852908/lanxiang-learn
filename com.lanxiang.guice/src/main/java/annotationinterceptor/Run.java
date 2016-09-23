package annotationinterceptor;

import annotationinterceptor.module.AnnotationModule;
import annotationinterceptor.service.RunService;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lanxiang on 16/9/18.
 */
public class Run {

    @Inject
    private RunService runService;

    @Before
    public void initModule() {
        Injector injector = Guice.createInjector(new AnnotationModule());
        runService = injector.getInstance(RunService.class);
    }

    @Test
    public void run(){
        runService.eat();

        runService.sleep();

        runService.work();
    }
}