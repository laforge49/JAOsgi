package org.agilewiki.jactor.lpc.syncTiming;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class Sender extends JLPCActor {
    public Echo echo;
    public long count;
    boolean async;
    boolean sync;
    int i = 0;

    public void sender(final ResponseProcessor rp) throws Exception {
        while (true) {
            i += 1;
            if (i > count) {
                rp.processResponse(null);
                return;
            }
            async = false;
            sync = false;
            DoEcho.req.send(this, echo, new ResponseProcessor<Object>() {
                @Override
                public void processResponse(Object response) throws Exception {
                    if (!async) {
                        sync = true;
                    } else {
                        sender(rp);
                    }
                }
            });
            if (!sync) {
                async = true;
                return;
            }
        }
    }
}
