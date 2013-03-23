package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Subtract extends RequestBase<Integer, _Calculator> {

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        _Calculator c = (_Calculator) targetActor;
        c.subtract(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof _Calculator;
    }

    public Subtract(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private int value;
}
