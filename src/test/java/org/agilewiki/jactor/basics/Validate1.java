package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Validate1 extends RequestBase<Boolean, Actor4> {
    public static final Validate1 req = new Validate1();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Actor4 a = (Actor4) targetActor;
        a.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Actor4;
    }
}
