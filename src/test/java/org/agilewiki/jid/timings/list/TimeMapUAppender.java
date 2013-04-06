package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class TimeMapUAppender extends RequestBase<Long, MapUAppender> {
    final static public TimeMapUAppender req = new TimeMapUAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapUAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((MapUAppender) targetActor).time());
    }
}
