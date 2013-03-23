package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class TimeMapSAppender extends RequestBase<Long, MapSAppender> {
    final static public TimeMapSAppender req = new TimeMapSAppender();

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof MapSAppender;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        rp.processResponse(((MapSAppender) targetActor).time());
    }
}
