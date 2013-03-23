package org.agilewiki.jactor.lpc.timingTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.TargetActor;

public interface RealRequestReceiver extends TargetActor {
    public void processRequest(RealRequest request, ResponseProcessor rp)
            throws Exception;
}
