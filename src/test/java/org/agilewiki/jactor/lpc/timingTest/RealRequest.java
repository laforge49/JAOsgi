package org.agilewiki.jactor.lpc.timingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class RealRequest extends RequestBase<Object, RealRequestReceiver> {
    public final static RealRequest req = new RealRequest();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof RealRequestReceiver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        RealRequestReceiver smDriver = (RealRequestReceiver) targetActor;
        smDriver.processRequest(this, rp);
    }
}
