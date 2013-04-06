package org.agilewiki.jactor.counterTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * Test code.
 */
final public class GetAndReset extends RequestBase<Long, CounterActor> {
    public long number;

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        CounterActor ca = (CounterActor) targetActor;
        ca.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof CounterActor;
    }
}
