package org.agilewiki.jactor.advanced.allInOne;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class Start extends AllInOneReq {
    public final static Start req = new Start();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((AllInOne) targetActor).start(rp);
    }
}
