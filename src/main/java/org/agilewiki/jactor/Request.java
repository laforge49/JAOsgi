package org.agilewiki.jactor;

import org.agilewiki.jactor.apc.APCRequestSource;
import org.agilewiki.jactor.old.JAFuture;

public interface Request <RESPONSE_TYPE> {
    public RESPONSE_TYPE send(JAFuture future) throws Exception;

    public void send(APCRequestSource requestSource, ResponseProcessor<RESPONSE_TYPE> rp) throws Exception;

    public void sendEvent() throws Exception;

    public void sendEvent(APCRequestSource requestSource) throws Exception;

    public void processRequest(ResponseProcessor<RESPONSE_TYPE> rp) throws Exception;
}
