package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.Request;

public class TimeBooleanSAppender extends Request<Long, BooleanSAppender> {
    final static public TimeBooleanSAppender req = new TimeBooleanSAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof BooleanSAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((BooleanSAppender) targetActor).time());
    }
}
