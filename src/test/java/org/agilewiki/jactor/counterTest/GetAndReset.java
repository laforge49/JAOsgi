package org.agilewiki.jactor.counterTest;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.Request;

/**
 * Test code.
 */
final public class GetAndReset extends Request<Long, CounterActor> {
    public long number;

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        CounterActor ca = (CounterActor) targetActor;
        ca.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof CounterActor;
    }
}
