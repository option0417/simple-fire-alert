package tw.com.wd.flow;

import org.junit.Assert;
import org.junit.Test;
import tw.com.wd.handler.*;

import java.lang.reflect.Field;

public class LineReplyServiceFlowTest {
    /**
     * LineSignatureHandler -> LineEventParserHandler -> LineEventDisplayHandler -> LineReplyHandler
     */
    @Test
    public void testLineReplyServiceFlow() {
        Throwable rtnThrowable = null;
        AbstractFireAlertHandler abstractFireAlertHandler = null;
        AbstractFireAlertHandler abstractFireAlertHandler1 = null;
        AbstractFireAlertHandler abstractFireAlertHandler2 = null;
        AbstractFireAlertHandler abstractFireAlertHandler3 = null;
        AbstractFireAlertHandler abstractFireAlertHandler4 = null;
        try {
            LineReplyServiceFlow lineReplyServiceFlow = new LineReplyServiceFlow();

            IFireAlertHandler fireAlertHandler = lineReplyServiceFlow.buildInitialFireAlertHandler();
            abstractFireAlertHandler = (AbstractFireAlertHandler) fireAlertHandler;
            abstractFireAlertHandler1 = getNextHandler(abstractFireAlertHandler);
            abstractFireAlertHandler2 = getNextHandler(abstractFireAlertHandler1);
            abstractFireAlertHandler3 = getNextHandler(abstractFireAlertHandler2);
            abstractFireAlertHandler4 = getNextHandler(abstractFireAlertHandler3);
        } catch (Throwable t) {
            t.printStackTrace();
            rtnThrowable = t;
        }

        Assert.assertNull(rtnThrowable);
        Assert.assertNotNull(abstractFireAlertHandler);
        Assert.assertNotNull(abstractFireAlertHandler1);
        Assert.assertNotNull(abstractFireAlertHandler2);
        Assert.assertNotNull(abstractFireAlertHandler3);
        Assert.assertNull(abstractFireAlertHandler4);

        Assert.assertTrue(abstractFireAlertHandler instanceof LineSignatureHandler);
        Assert.assertTrue(abstractFireAlertHandler1 instanceof LineEventParserHandler);
        Assert.assertTrue(abstractFireAlertHandler2 instanceof LineEventDisplayHandler);
        Assert.assertTrue(abstractFireAlertHandler3 instanceof LineReplyHandler);

    }

    private AbstractFireAlertHandler getNextHandler(AbstractFireAlertHandler abstractFireAlertHandler) throws IllegalAccessException, NoSuchFieldException {
        Field field = AbstractFireAlertHandler.class.getDeclaredField("nextHandler");
        field.setAccessible(true);

        return field.get(abstractFireAlertHandler) != null ? (AbstractFireAlertHandler)field.get(abstractFireAlertHandler) : null;
    }

}
