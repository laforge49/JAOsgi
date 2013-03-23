package org.agilewiki.jactor.pubsub.latency;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.TargetActor;

/**
 * Test code.
 */
public interface Src extends TargetActor {
    public void go(ResponseProcessor rp) throws Exception;
}
