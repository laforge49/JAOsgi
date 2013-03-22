package org.agilewiki.jactor.counterTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
final public class AddCount extends RequestBase<Object, CounterActor> {
    public long number;

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        CounterActor ca = (CounterActor) targetActor;
        ca.processRequest(this, rp);
    }
}
