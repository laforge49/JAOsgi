package org.agilewiki.jactor.lpc.syncTiming;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class DoSender extends RequestBase<Object, Sender> {
    public final static DoSender req = new DoSender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Sender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Sender) targetActor).sender(rp);
    }
}
