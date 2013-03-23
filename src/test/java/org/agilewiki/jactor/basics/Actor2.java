package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;

/**
 * Test code.
 */
public class Actor2 extends Greeter {
    @Override
    public void processRequest(Greet1 request, final ResponseProcessor rp) throws Exception {
        Hi1.req.send(this, Actor1.get(this), new ResponseProcessor<String>() {
            @Override
            public void processResponse(String response) throws Exception {
                System.out.println(response);
                rp.processResponse(null);
            }
        });
    }
}
