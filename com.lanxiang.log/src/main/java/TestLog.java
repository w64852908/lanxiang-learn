import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/8/31.
 */
public class TestLog {

    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    @Test
    public void logTest() {
        int status = 0;
        if (status == 0) {
            logger.info("status:{}", status);
        } else {
            logger.info("status:{}", status);
        }
        logger.info("end!");
    }
}
