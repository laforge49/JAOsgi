package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

import static junit.framework.Assert.assertEquals;

public class Doer extends JLPCActor {
    ResponseProcessor pending;

    public void release(ResponseProcessor rp)
            throws Exception {
        this.pending = rp;
        assertEquals(
                Release.req,
                getMailbox().getCurrentRequest().getUnwrappedRequest());
    }

    public void allocate()
            throws Exception {
        assertEquals(
                Allocate.req,
                getMailbox().getCurrentRequest().getUnwrappedRequest());
        pending.processResponse(null);
    }
}
