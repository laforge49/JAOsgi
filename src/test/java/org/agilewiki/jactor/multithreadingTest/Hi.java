package org.agilewiki.jactor.multithreadingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * Test code.
 */
public class Hi extends RequestBase<String, Greeter> {
    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Greeter;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((Greeter) targetActor).hi());
    }
}