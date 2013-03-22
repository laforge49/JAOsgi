package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

public class TimeBooleanUAppender extends RequestBase<Long, BooleanUAppender> {
    final static public TimeBooleanUAppender req = new TimeBooleanUAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof BooleanUAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((BooleanUAppender) targetActor).time());
    }
}
