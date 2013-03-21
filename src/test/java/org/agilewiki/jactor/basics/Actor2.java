package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Actor2 extends Greeter {
    @Override
    public void processRequest(Greet1 request, final RP rp) throws Exception {
        Hi1.req.send(this, Actor1.get(this), new RP<String>() {
            @Override
            public void processResponse(String response) throws Exception {
                System.out.println(response);
                rp.processResponse(null);
            }
        });
    }
}
