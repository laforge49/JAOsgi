package org.agilewiki.jactor.lpc.serverTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class WorkRequest extends RequestBase<Object, Worker> {
    public int id;

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        Worker w = (Worker) targetActor;
        w.processRequest(this, rp);
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Worker;
    }
}
