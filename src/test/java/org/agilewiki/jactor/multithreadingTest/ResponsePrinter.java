package org.agilewiki.jactor.multithreadingTest;

import org.agilewiki.jactor.Ancestor;
import org.agilewiki.jactor.JActor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.lpc.Request;

/**
 * Test code.
 */
public class ResponsePrinter extends JLPCActor {
    public static ResponsePrinter get(Ancestor ancestor) {
        return (ResponsePrinter) JActor.getMatch(ancestor, ResponsePrinter.class);
    }

    public void printResponse(Request wrappedRequest, Actor actor, final RP rp) throws Exception {
        wrappedRequest.send(this, actor, new RP() {
            @Override
            public void processResponse(Object response) throws Exception {
                System.out.println(response);
                rp.processResponse(null);
            }
        });
    }
}
