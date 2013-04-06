package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class Release extends RequestBase<Object, Doer> {
    public final static Release req = new Release();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof ReleaseDriver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Doer) targetActor).release(rp);
    }
}
