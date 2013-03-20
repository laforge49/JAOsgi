package org.agilewiki.jactor;

import org.agilewiki.jactor.lpc.TargetActor;
import org.agilewiki.jactor.old.RP;

public interface SimpleRequestReceiver extends TargetActor {
    public void processRequest(SimpleRequest request, RP rp)
            throws Exception;
}
