package org.agilewiki.jactor;


import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class SimpleRequest extends RequestBase<Object, SimpleRequestReceiver> {
    public final static SimpleRequest req = new SimpleRequest();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof SimpleRequestReceiver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        SimpleRequestReceiver smDriver = (SimpleRequestReceiver) targetActor;
        smDriver.processRequest(this, rp);
    }
}
