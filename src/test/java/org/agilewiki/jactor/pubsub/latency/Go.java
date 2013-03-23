package org.agilewiki.jactor.pubsub.latency;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

/**
 * Test code.
 */
public class Go extends RequestBase<Object, Src> {
    public final static Go req = new Go();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Src;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Src) targetActor).go(rp);
    }
}
