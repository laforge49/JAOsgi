package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;

/**
 * Test code.
 */
public class Driver1 extends SimpleRequestReceiver {
    @Override
    public void processRequest(SimpleRequest request, final ResponseProcessor rp) throws Exception {
        final Calculator calculator = new Calculator();
        calculator.initialize(getMailbox());
        send(calculator, new Set(1), new ResponseProcessor() {
            @Override
            public void processResponse(Object response) throws Exception {
                (new Add(2)).send(Driver1.this, calculator, new ResponseProcessor() {
                    @Override
                    public void processResponse(Object response) throws Exception {
                        (new Multiply(3)).send(Driver1.this, calculator, rp);
                    }
                });
            }
        });
    }
}
