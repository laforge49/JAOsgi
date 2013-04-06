package org.agilewiki.jactor.lpc.timingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * A wrapper of an array of requests to be sent to multiple actors.
 */
public class RunParallel extends RequestBase<Object, JAParallel> {
    /**
     * The wrapped requests;
     */
    private RequestBase[] requests;

    /**
     * Returns the wrapped requests.
     *
     * @return The wrapped requests.
     */
    public RequestBase[] getRequests() {
        return requests;
    }

    /**
     * Create the request.
     *
     * @param requests the requests to be run in parallel.
     */
    public RunParallel(RequestBase[] requests) {
        this.requests = requests;
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof JAParallel;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((JAParallel) targetActor).runParallel(requests, rp);
    }
}
