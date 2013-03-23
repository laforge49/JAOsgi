package org.agilewiki.jactor.advanced.allInOne;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

public class Allocate extends AllInOneReq {
    public final static Allocate req = new Allocate();

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((AllInOne) targetActor).allocate(rp);
    }
}
