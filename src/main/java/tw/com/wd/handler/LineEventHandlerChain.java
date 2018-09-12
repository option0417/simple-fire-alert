package tw.com.wd.handler;

import tw.com.wd.obj.FireAlertObj;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class LineEventHandlerChain {
    private ExecutorService executorService;
    private IFireAlertHandler initialHandler;
    private IFireAlertHandler lineEventParserHandler;
    private IFireAlertHandler lineSignatureHandler;
    private IFireAlertHandler lineEventDisplayHandler;
    private IFireAlertHandler lineReplyHandler;


    public LineEventHandlerChain() {
        super();
        executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "LineEvent");
            }
        });

        initialHandler = lineSignatureHandler = new LineSignatureHandler();
        lineEventParserHandler  = new LineEventParserHandler();
        lineEventDisplayHandler = new LineEventDisplayHandler();
        lineReplyHandler        = new LineReplyHandler();

        ((AbstractFireAlertHandler)lineSignatureHandler)
                .addNextHandler(lineEventParserHandler)
                .addNextHandler(lineEventDisplayHandler)
                .addNextHandler(lineReplyHandler);
    }

    public void doHandler(FireAlertObj fireAlertObj) {
        executorService.submit(new ChainTrigger(fireAlertObj, this.initialHandler));
    }


    private class ChainTrigger implements Callable<FireAlertObj> {
        private FireAlertObj fireAlertObj;
        private IFireAlertHandler chainStarter;


        public ChainTrigger(FireAlertObj fireAlertObj, IFireAlertHandler chainStarter) {
            super();

            if (fireAlertObj == null || chainStarter == null) {
                throw new IllegalArgumentException("The fireAlertObj or chainStarter is null");
            }

            this.fireAlertObj = fireAlertObj;
            this.chainStarter = chainStarter;
        }

        @Override
        public FireAlertObj call() throws Exception {
            this.chainStarter.doHandler(fireAlertObj);
            return fireAlertObj;
        }
    }
}
