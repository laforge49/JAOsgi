package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class TimeMapAppender extends RequestBase<Long, MapAppender> {
    final static public TimeMapAppender req = new TimeMapAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((MapAppender) targetActor).time());
    }
}
