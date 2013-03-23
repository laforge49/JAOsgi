package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Actor5 extends JLPCActor {
    public long delay;

    protected void processRequest(Delay request, final ResponseProcessor rp) throws Exception {
        Thread.sleep(delay);
        rp.processResponse(null);
    }
}
