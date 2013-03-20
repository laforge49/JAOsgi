package org.agilewiki.jactor.pubsub.latency;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.Request;

/**
 * Test code.
 */
public class Go extends Request<Object, Src> {
    public final static Go req = new Go();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Src;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((Src) targetActor).go(rp);
    }
}
