package org.agilewiki.jactor;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class IntegerRequest extends RequestBase<Object, IntegerReceiver> {
    public final int value;

    public IntegerRequest(int value) {
        this.value = value;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp)
            throws Exception {
        IntegerReceiver ir = (IntegerReceiver) targetActor;
        ir.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof IntegerReceiver;
    }
}
