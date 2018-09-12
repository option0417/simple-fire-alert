package tw.com.wd.flow;

import tw.com.wd.handler.*;

public class FireAlertDeliveryServiceFlow extends AbstractFireAlertFlow implements IFireAlertServiceFlow {
    private IFireAlertHandler dataFetcherHandler;
    private IFireAlertHandler dataParserHandler;
    private IFireAlertHandler dataDeliverHandler;

    @Override
    protected IFireAlertHandler buildInitialFireAlertHandler() {
        dataFetcherHandler  = new DataFetcherHandler();
        dataParserHandler   = new DataParserHandler();
        dataDeliverHandler  = new DataDeliverHandler();

        ((AbstractFireAlertHandler)dataFetcherHandler)
                .addNextHandler(dataParserHandler)
                .addNextHandler(dataDeliverHandler);
        return dataFetcherHandler;
    }
}
