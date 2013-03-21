package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.Ancestor;
import org.agilewiki.jactor.JActor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.TargetActor;

/**
 * Test code.
 */
public abstract class Greeter extends JLPCActor {
    public static Greeter get(Ancestor ancestor) {
        return (Greeter) JActor.getMatch(ancestor, Greeter.class);
    }

    abstract void processRequest(Greet1 request, final RP rp) throws Exception;
}
