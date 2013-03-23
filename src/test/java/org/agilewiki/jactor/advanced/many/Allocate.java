package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class Allocate extends RequestBase<Object, Doer> {
    public final static Allocate req = new Allocate();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof AllocateDriver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Doer) targetActor).allocate();
        rp.processResponse(null);
    }
}
