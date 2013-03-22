package org.agilewiki.jactor.multithreadingTest;

import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ancestor.AncestorBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;

/**
 * Test code.
 */
public class ResponsePrinter extends JLPCActor {
    public static ResponsePrinter get(Ancestor ancestor) {
        return (ResponsePrinter) AncestorBase.getMatch(ancestor, ResponsePrinter.class);
    }

    public void printResponse(RequestBase wrappedRequest, Actor actor, final RP rp) throws Exception {
        wrappedRequest.send(this, actor, new RP() {
            @Override
            public void processResponse(Object response) throws Exception {
                System.out.println(response);
                rp.processResponse(null);
            }
        });
    }
}
