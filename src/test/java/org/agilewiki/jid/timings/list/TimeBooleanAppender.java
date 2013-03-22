package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

public class TimeBooleanAppender extends RequestBase<Long, BooleanAppender> {
    final static public TimeBooleanAppender req = new TimeBooleanAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof BooleanAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((BooleanAppender) targetActor).time());
    }
}
