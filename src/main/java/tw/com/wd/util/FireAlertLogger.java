package tw.com.wd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireAlertLogger {
    private static final Logger LOG = LoggerFactory.getLogger("FireAlert");

    public static void info(String info) {
        LOG.info(info);
    }

    public static void info(String format, String... info) {
        LOG.info(format, info);
    }

    public static void debug(String debug) {
        LOG.debug(debug);
    }

    public static void debug(String format, String... debug) {
        LOG.debug(format, debug);
    }

    public static void error(String error) {
        LOG.error(error);
    }

    public static void error(String format, String... debug) {
        LOG.error(format, debug);
    }
}
