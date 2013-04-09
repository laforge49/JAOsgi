package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class TimeBooleanUAppender extends RequestBase<Long, BooleanUAppender> {
    final static public TimeBooleanUAppender req = new TimeBooleanUAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof BooleanUAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((BooleanUAppender) targetActor).time());
    }
}
