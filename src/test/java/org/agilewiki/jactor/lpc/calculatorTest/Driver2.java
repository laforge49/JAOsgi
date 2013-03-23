package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;

/**
 * Test code.
 */
public class Driver2 extends SimpleRequestReceiver {
    @Override
    public void processRequest(SimpleRequest request, final ResponseProcessor rp)
            throws Exception {
        final Calculator calculator = new Calculator();
        calculator.initialize(getMailbox());
        (new Set(1)).sendEvent(this, calculator);
        (new Add(2)).sendEvent(this, calculator);
        (new Multiply(3)).send(this, calculator, rp);
    }
}
