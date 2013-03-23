package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ancestor.AncestorBase;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public abstract class Greeter extends JLPCActor {
    public static Greeter get(Ancestor ancestor) {
        return (Greeter) AncestorBase.getMatch(ancestor, Greeter.class);
    }

    abstract void processRequest(Greet1 request, final ResponseProcessor rp) throws Exception;
}
