package tw.com.wd.handler;

import tw.com.wd.obj.FireAlertObj;

public class LineEventHandlerChain {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;
    private IFireAlertHandler lineSignatureHandler;
    private IFireAlertHandler lineEventDisplayHandler;


    public LineEventHandlerChain() {
        super();

        initialHandler = lineSignatureHandler = new LineSignatureHandler();
        lineEventParserHandler = new LineEventParserHandler();
        lineEventDisplayHandler = new LineEventDisplayHandler();

        ((AbstractFireAlertHandler)lineSignatureHandler).addNextHandler(lineEventParserHandler).addNextHandler(lineEventDisplayHandler);
    }

    public void doHandler(FireAlertObj fireAlertObj) {
        initialHandler.doHandler(fireAlertObj);
    }
}
