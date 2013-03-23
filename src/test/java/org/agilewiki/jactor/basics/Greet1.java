package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Greet1 extends RequestBase<Object, Greeter> {
    public static final Greet1 req = new Greet1();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Greeter a = (Greeter) targetActor;
        a.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Greeter;
    }
}
