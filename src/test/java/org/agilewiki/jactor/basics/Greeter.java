package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.Ancestor;
import org.agilewiki.jactor.AncestorActor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.RP;

/**
 * Test code.
 */
public abstract class Greeter extends JLPCActor {
    public static Greeter get(Ancestor ancestor) {
        return (Greeter) AncestorActor.getMatch(ancestor, Greeter.class);
    }

    abstract void processRequest(Greet1 request, final RP rp) throws Exception;
}
