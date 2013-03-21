package org.agilewiki.jactor;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.TargetActor;
import org.agilewiki.jactor.old.RP;

public abstract class SimpleRequestReceiver extends JLPCActor {
    abstract public void processRequest(SimpleRequest request, RP rp)
            throws Exception;
}
