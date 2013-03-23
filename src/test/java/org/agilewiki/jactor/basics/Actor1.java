package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ancestor.AncestorBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Actor1 extends JLPCActor {
    public static Actor1 get(Ancestor ancestor) {
        return (Actor1) AncestorBase.getMatch(ancestor, Actor1.class);
    }

    protected void processRequest(Hi1 request, ResponseProcessor rp) throws Exception {
        rp.processResponse("Hello world!");
    }
}
