package org.agilewiki.jactor.iteratorTest.factorialTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Multiplier extends JLPCActor {
    public void processRequest(Multiply m, ResponseProcessor rp)
            throws Exception {
        rp.processResponse(new Integer(m.a * m.b));
    }
}
