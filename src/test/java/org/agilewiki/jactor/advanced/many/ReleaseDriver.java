package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class ReleaseDriver extends JLPCActor {
    public Doer doer;

    public void startRelease(final ResponseProcessor rp)
            throws Exception {
        Release.req.send(this, doer, new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                assertEquals(
                        StartRelease.req,
                        getMailbox().getCurrentRequest().getUnwrappedRequest());
                rp.processResponse(null);
                assertEquals(
                        StartRelease.req,
                        getMailbox().getCurrentRequest().getUnwrappedRequest());
            }
        });
        getMailbox().sendPendingMessages();
    }
}
