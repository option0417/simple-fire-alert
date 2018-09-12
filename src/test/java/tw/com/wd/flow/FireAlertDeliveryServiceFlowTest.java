package tw.com.wd.flow;

import org.junit.Assert;
import org.junit.Test;
import tw.com.wd.handler.*;

import java.lang.reflect.Field;

public class FireAlertDeliveryServiceFlowTest {
    /**
     * DataFetcherHandler -> DataParserHandler -> DataDeliverHandler
     */
    @Test
    public void testFireAlertDeliveryServiceFlow() {
        Throwable rtnThrowable = null;
        AbstractFireAlertHandler abstractFireAlertHandler = null;
        AbstractFireAlertHandler abstractFireAlertHandler1 = null;
        AbstractFireAlertHandler abstractFireAlertHandler2 = null;
        AbstractFireAlertHandler abstractFireAlertHandler3 = null;
        try {
            FireAlertDeliveryServiceFlow fireAlertDeliveryServiceFlow = new FireAlertDeliveryServiceFlow();

            IFireAlertHandler fireAlertHandler = fireAlertDeliveryServiceFlow.buildInitialFireAlertHandler();
            abstractFireAlertHandler = (AbstractFireAlertHandler) fireAlertHandler;
            abstractFireAlertHandler1 = getNextHandler(abstractFireAlertHandler);
            abstractFireAlertHandler2 = getNextHandler(abstractFireAlertHandler1);
            abstractFireAlertHandler3 = getNextHandler(abstractFireAlertHandler2);
        } catch (Throwable t) {
            t.printStackTrace();
            rtnThrowable = t;
        }

        Assert.assertNull(rtnThrowable);
        Assert.assertNotNull(abstractFireAlertHandler);
        Assert.assertNotNull(abstractFireAlertHandler1);
        Assert.assertNotNull(abstractFireAlertHandler2);
        Assert.assertNull(abstractFireAlertHandler3);

        Assert.assertTrue(abstractFireAlertHandler instanceof DataFetcherHandler);
        Assert.assertTrue(abstractFireAlertHandler1 instanceof DataParserHandler);
        Assert.assertTrue(abstractFireAlertHandler2 instanceof DataDeliverHandler);

    }

    private AbstractFireAlertHandler getNextHandler(AbstractFireAlertHandler abstractFireAlertHandler) throws IllegalAccessException, NoSuchFieldException {
        Field field = AbstractFireAlertHandler.class.getDeclaredField("nextHandler");
        field.setAccessible(true);

        return field.get(abstractFireAlertHandler) != null ? (AbstractFireAlertHandler)field.get(abstractFireAlertHandler) : null;
    }
}
