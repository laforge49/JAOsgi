package org.agilewiki.jactor.lpc.exceptionsTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class Go2 extends RequestBase<Object, GoReceiver> {
    public final static Go2 req = new Go2();

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
