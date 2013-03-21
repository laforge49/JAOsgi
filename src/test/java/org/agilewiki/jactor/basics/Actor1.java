package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ancestor.AncestorActor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Actor1 extends JLPCActor {
    public static Actor1 get(Ancestor ancestor) {
        return (Actor1) AncestorActor.getMatch(ancestor, Actor1.class);
    }

    protected void processRequest(Hi1 request, RP rp) throws Exception {
        rp.processResponse("Hello world!");
    }
}
