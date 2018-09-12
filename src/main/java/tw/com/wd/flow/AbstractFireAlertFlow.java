package tw.com.wd.flow;

import tw.com.wd.handler.IFireAlertHandler;
import tw.com.wd.obj.FireAlertObj;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public abstract class AbstractFireAlertFlow implements IFireAlertServiceFlow {
    private ExecutorService executorService;

    public AbstractFireAlertFlow() {
        super();
        executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, getClass().getSimpleName());
            }
        });
    }

    @Override
    public void trigger(FireAlertObj fireAlertObj) {
        IFireAlertHandler initFireAlertHandler = buildInitialFireAlertHandler();
        executorService.submit(new ChainTrigger(fireAlertObj, initFireAlertHandler));

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

    protected abstract IFireAlertHandler buildInitialFireAlertHandler();
}
