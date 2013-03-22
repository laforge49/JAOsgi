package org.agilewiki.jactor.lpc.syncTiming;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

public class DoEcho extends RequestBase<Object, Echo> {
    public final static DoEcho req = new DoEcho();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Echo;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((Echo) targetActor).echo();
        rp.processResponse(null);
    }
}
