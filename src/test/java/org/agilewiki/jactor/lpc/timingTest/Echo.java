package org.agilewiki.jactor.lpc.timingTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;

/**
 * Test code.
 */
final public class Echo extends SimpleRequestReceiver {
    @Override
    public void processRequest(SimpleRequest unwrappedRequest, ResponseProcessor responseProcessor)
            throws Exception {
        responseProcessor.processResponse(null);
    }
}
