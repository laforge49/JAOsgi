package org.agilewiki.jactor.lpc.timingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * A wrapper of a request to be sent to multiple actors.
 */
public class Run1Parallel extends RequestBase<Object, JAParallel> {
    /**
     * The wrapped request;
     */
    private RequestBase request;

    /**
     * Returns the wrapped request.
     *
     * @return The wrapped request.
     */
    public RequestBase getRequest() {
        return request;
    }

    /**
     * Create the request.
     *
     * @param request the request to be run in parallel.
     */
    public Run1Parallel(RequestBase request) {
        this.request = request;
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof JAParallel;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((JAParallel) targetActor).run1Parallel(request, rp);
    }
}
