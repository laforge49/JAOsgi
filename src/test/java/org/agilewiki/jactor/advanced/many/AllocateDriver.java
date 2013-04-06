package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class AllocateDriver extends JLPCActor {
    public Doer doer;

    public void startAllocate(final ResponseProcessor rp)
            throws Exception {
        Allocate.req.send(this, doer, new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                assertEquals(
                        StartAllocate.req,
                        getMailbox().getCurrentRequest().getUnwrappedRequest());
                rp.processResponse(null);
                /*
                assertEquals(
                        StartAllocate.req,
                        getMailbox().getCurrentRequest().getUnwrappedRequest());
                        */
            }
        });
    }
}
