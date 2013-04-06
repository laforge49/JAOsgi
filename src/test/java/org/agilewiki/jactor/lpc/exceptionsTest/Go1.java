package org.agilewiki.jactor.lpc.exceptionsTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class Go1 extends RequestBase<Object, GoReceiver> {
    public final static Go1 req = new Go1();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof GoReceiver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        GoReceiver smDriver = (GoReceiver) targetActor;
        smDriver.processRequest(this, rp);
    }
}
