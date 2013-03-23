package org.agilewiki.jactor.lpc.exceptionsTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.TargetActor;

public interface GoReceiver extends TargetActor {
    public void processRequest(Go1 request, ResponseProcessor rp)
            throws Exception;

    public void processRequest(Go2 request, ResponseProcessor rp)
            throws Exception;
}
