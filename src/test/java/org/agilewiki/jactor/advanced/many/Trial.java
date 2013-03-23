package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class Trial extends RequestBase<Object, Driver> {
    public final static Trial req = new Trial();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Driver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Driver) targetActor).trial(rp);
    }
}
