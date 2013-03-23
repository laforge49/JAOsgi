package org.agilewiki.jactor;

import org.agilewiki.jactor.lpc.TargetActor;

public interface IntegerReceiver extends TargetActor {
    public void processRequest(IntegerRequest ir, ResponseProcessor rp)
            throws Exception;
}
