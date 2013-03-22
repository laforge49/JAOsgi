package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

/**
 * Test code.
 */
public class Add extends RequestBase<Integer, _Calculator> {

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        _Calculator c = (_Calculator) targetActor;
        c.add(this, rp);
    }

    public Add(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private int value;
}
