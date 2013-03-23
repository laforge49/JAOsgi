package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.ExceptionHandler;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Actor4 extends JLPCActor {
    protected void processRequest(final Validate1 request, final ResponseProcessor rp)
            throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                rp.processResponse(false);
            }
        });
        Greet1.req.send(this, Greeter.get(this), new ResponseProcessor<Object>() {
            @Override
            public void processResponse(final Object response) throws Exception {
                rp.processResponse(true);
            }
        });
    }
}
