package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Hi1 extends RequestBase<String, Actor1> {
    public static final Hi1 req = new Hi1();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        Actor1 a = (Actor1) targetActor;
        a.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Actor1;
    }
}
