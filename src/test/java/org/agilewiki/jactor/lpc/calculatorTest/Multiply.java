package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.Request;

/**
 * Test code.
 */
public class Multiply extends Request<Integer, _Calculator> {

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        _Calculator c = (_Calculator) targetActor;
        c.multiply(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof _Calculator;
    }

    public Multiply(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private int value;
}
