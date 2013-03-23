package org.agilewiki.jactor;

import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ancestor.AncestorBase;
import org.agilewiki.jactor.lpc.JLPCActor;

public abstract class SimpleRequestReceiver extends JLPCActor {
    public static SimpleRequestReceiver get(Ancestor ancestor) {
        return (SimpleRequestReceiver) AncestorBase.getMatch(ancestor, SimpleRequestReceiver.class);
    }

    abstract public void processRequest(SimpleRequest request, ResponseProcessor rp)
            throws Exception;
}
