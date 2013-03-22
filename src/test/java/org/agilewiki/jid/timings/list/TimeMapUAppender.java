package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

public class TimeMapUAppender extends RequestBase<Long, MapUAppender> {
    final static public TimeMapUAppender req = new TimeMapUAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapUAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        rp.processResponse(((MapUAppender) targetActor).time());
    }
}
