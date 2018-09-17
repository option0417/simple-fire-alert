package tw.com.wd.flow;

import tw.com.wd.handler.*;

public class LineReplyServiceFlow extends AbstractFireAlertFlow implements IFireAlertServiceFlow {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;
    private IFireAlertHandler lineSignatureHandler;
    private IFireAlertHandler lineEventDisplayHandler;
    private IFireAlertHandler lineReplyHandler;
    private IFireAlertHandler lineReplyDeliverHandler;

    @Override
    protected IFireAlertHandler buildInitialFireAlertHandler() {
        lineSignatureHandler    = new LineSignatureHandler();
        lineEventParserHandler  = new LineEventParserHandler();
        lineEventDisplayHandler = new LineEventDisplayHandler();
        lineReplyHandler        = new LineReplyHandler();
        lineReplyDeliverHandler = new LineReplyDeliverHandler();

        ((AbstractFireAlertHandler)lineSignatureHandler)
                .addNextHandler(lineEventParserHandler)
                .addNextHandler(lineEventDisplayHandler)
                .addNextHandler(lineReplyHandler)
                .addNextHandler(lineReplyDeliverHandler);
        return lineSignatureHandler;
    }
}
