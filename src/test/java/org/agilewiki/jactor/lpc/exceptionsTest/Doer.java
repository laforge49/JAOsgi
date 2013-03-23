package org.agilewiki.jactor.lpc.exceptionsTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Doer extends JLPCActor implements GoReceiver {
    @Override
    public void processRequest(Go1 request, ResponseProcessor rp) throws Exception {
        throw new Exception("Exception thrown in request processing");
    }

    @Override
    public void processRequest(Go2 request, ResponseProcessor rp) throws Exception {
        rp.processResponse(request);
    }
}
