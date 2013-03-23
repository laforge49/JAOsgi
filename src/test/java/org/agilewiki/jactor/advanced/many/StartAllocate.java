package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class StartAllocate extends RequestBase<Object, AllocateDriver> {
    public final static StartAllocate req = new StartAllocate();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof AllocateDriver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((AllocateDriver) targetActor).startAllocate(rp);
    }
}
