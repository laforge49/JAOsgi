package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

public class TimeMapDAppender extends RequestBase<Long, MapDAppender> {
    final static public TimeMapDAppender req = new TimeMapDAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapDAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((MapDAppender) targetActor).time());
    }
}
