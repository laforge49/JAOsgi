package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Parallel extends RequestBase<Boolean, Actor5a> {
    public static final Parallel req = new Parallel();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Actor5a a = (Actor5a) targetActor;
        a.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Actor5a;
    }
}
