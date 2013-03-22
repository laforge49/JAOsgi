package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

public class TimeMapAppender extends RequestBase<Long, MapAppender> {
    final static public TimeMapAppender req = new TimeMapAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((MapAppender) targetActor).time());
    }
}
