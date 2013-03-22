package org.agilewiki.jactor;

import org.agilewiki.jactor.apc.APCRequestSource;
import org.agilewiki.jactor.lpc.TargetActor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.RP;

public interface Request <RESPONSE_TYPE> {
    public RESPONSE_TYPE send(JAFuture future) throws Exception;

    public void send(APCRequestSource requestSource, RP<RESPONSE_TYPE> rp) throws Exception;

    public void sendEvent() throws Exception;

    public void sendEvent(APCRequestSource requestSource) throws Exception;

    public void processRequest(RP rp) throws Exception;
}
