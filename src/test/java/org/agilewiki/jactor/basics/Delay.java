package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

/**
 * Test code.
 */
public class Delay extends RequestBase<Object, Actor5> {
    public static final Delay req = new Delay();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Actor5 a = (Actor5) targetActor;
        a.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Actor5;
    }
}
