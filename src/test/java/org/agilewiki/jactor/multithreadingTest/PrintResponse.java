package org.agilewiki.jactor.multithreadingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class PrintResponse<RESPONSE_TYPE> extends RequestBase<Object, ResponsePrinter> {
    private RequestBase<RESPONSE_TYPE, ?> request;
    private Actor actor;

    public PrintResponse(RequestBase<RESPONSE_TYPE, ?> request, Actor actor) {
        this.request = request;
        this.actor = actor;
    }

    public RequestBase<RESPONSE_TYPE, ?> getRequest() {
        return request;
    }

    public Actor getActor() {
        return actor;
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof ResponsePrinter;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((ResponsePrinter) targetActor).printResponse(request, actor, rp);
    }
}
