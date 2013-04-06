package org.agilewiki.jactor.iteratorTest.factorialTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * Test code.
 */
public class Multiply extends RequestBase<Integer, Multiplier> {
    public int a;
    public int b;

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Multiplier m = (Multiplier) targetActor;
        m.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Multiplier;
    }
}
