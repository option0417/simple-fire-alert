package tw.com.wd.service.handler;

import tw.com.wd.service.obj.FireAlertObj;

public class FireAlertHandlerChain {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler dataFetcherHandler;
    private IFireAlertHandler dataParserHandler;
    private IFireAlertHandler dataDeliverHandler;


    public FireAlertHandlerChain() {
        super();

        dataFetcherHandler = new DataFetcherHandler();
        dataParserHandler = new DataParserHandler();
        dataDeliverHandler = new DataDeliverHandler();

        ((AbstractFireAlertHandler)dataFetcherHandler).addNextHandler(dataParserHandler).addNextHandler(dataDeliverHandler);

        initialHandler = dataFetcherHandler;
    }

    public void doHandler(FireAlertObj fireAlertObj) {
        initialHandler.doHandler(fireAlertObj);
    }
}
