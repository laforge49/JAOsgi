package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * Test code.
 */
public class Clear extends RequestBase<Integer, _Calculator> {

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        _Calculator c = (_Calculator) targetActor;
        c.clear(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof _Calculator;
    }

}
