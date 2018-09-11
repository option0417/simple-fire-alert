package tw.com.wd.handler;

import tw.com.wd.obj.FireAlertObj;

public class LineEventHandlerChain {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;
    private IFireAlertHandler lineSignatureHandler;


    public LineEventHandlerChain() {
        super();

        initialHandler = lineSignatureHandler = new LineSignatureHandler();

        lineEventParserHandler = new LineEventParserHandler();

        ((AbstractFireAlertHandler)lineSignatureHandler).addNextHandler(lineEventParserHandler);
    }

    public void doHandler(FireAlertObj fireAlertObj) {
        initialHandler.doHandler(fireAlertObj);
    }
}
