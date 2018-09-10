package tw.com.wd.handler;

import tw.com.wd.obj.FireAlertObj;

public class LineEventHandlerChain {
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;


    public LineEventHandlerChain() {
        super();
        initialHandler = lineEventParserHandler = new LineEventParserHandler();
    }

    public void doHandler(FireAlertObj fireAlertObj) {
        initialHandler.doHandler(fireAlertObj);
    }
}
