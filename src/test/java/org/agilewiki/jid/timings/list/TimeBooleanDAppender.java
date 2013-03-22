package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

public class TimeBooleanDAppender extends RequestBase<Long, BooleanDAppender> {
    final static public TimeBooleanDAppender req = new TimeBooleanDAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof BooleanDAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((BooleanDAppender) targetActor).time());
    }
}
