package org.agilewiki.jactor.pubsub.latency;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

/**
 * Test code.
 */
public class Ping extends RequestBase<Object, Sub> {
    public final static Ping req = new Ping();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Sub;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((Sub) targetActor).ping();
        rp.processResponse(null);
    }
}
