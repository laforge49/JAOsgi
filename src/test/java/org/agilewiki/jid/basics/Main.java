package org.agilewiki.jid.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.TargetActor;

public interface Main extends TargetActor {
    public void processRequest(Proc req, ResponseProcessor rp) throws Exception;
}
