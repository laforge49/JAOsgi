package org.agilewiki.jactor.advanced.many;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class StartRelease extends RequestBase<Object, ReleaseDriver> {
    public final static StartRelease req = new StartRelease();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof ReleaseDriver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((ReleaseDriver) targetActor).startRelease(rp);
    }
}
