package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

public class Release extends RequestBase<Object, Doer> {
    public final static Release req = new Release();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof ReleaseDriver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((Doer) targetActor).release(rp);
    }
}
