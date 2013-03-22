package org.agilewiki.jactor.lpc.syncTiming;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

public class DoSender extends RequestBase<Object, Sender> {
    public final static DoSender req = new DoSender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Sender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((Sender) targetActor).sender(rp);
    }
}
