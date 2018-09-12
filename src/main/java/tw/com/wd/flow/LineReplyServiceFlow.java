package tw.com.wd.flow;

import tw.com.wd.handler.*;

public class LineReplyServiceFlow extends AbstractFireAlertFlow implements IFireAlertServiceFlow {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;
    private IFireAlertHandler lineSignatureHandler;
    private IFireAlertHandler lineEventDisplayHandler;
    private IFireAlertHandler lineReplyHandler;

    @Override
    protected IFireAlertHandler buildInitialFireAlertHandler() {
        initialHandler = lineSignatureHandler = new LineSignatureHandler();
        lineEventParserHandler  = new LineEventParserHandler();
        lineEventDisplayHandler = new LineEventDisplayHandler();
        lineReplyHandler        = new LineReplyHandler();

        ((AbstractFireAlertHandler)lineSignatureHandler)
                .addNextHandler(lineEventParserHandler)
                .addNextHandler(lineEventDisplayHandler)
                .addNextHandler(lineReplyHandler);
        return initialHandler;
    }
}
