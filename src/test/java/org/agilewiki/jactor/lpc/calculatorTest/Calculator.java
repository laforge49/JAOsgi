package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Calculator extends JLPCActor implements _Calculator {
    private int accumulator;

    public void clear(Clear request, ResponseProcessor rp) throws Exception {
        accumulator = 0;
        rp.processResponse(new Integer(accumulator));
    }

    public void get(Get request, ResponseProcessor rp) throws Exception {
        rp.processResponse(new Integer(accumulator));
    }

    public void set(Set request, ResponseProcessor rp) throws Exception {
        accumulator = request.getValue();
        rp.processResponse(new Integer(accumulator));
    }

    public void add(Add request, ResponseProcessor rp) throws Exception {
        accumulator = accumulator + request.getValue();
        rp.processResponse(new Integer(accumulator));
    }

    public void subtract(Subtract request, ResponseProcessor rp) throws Exception {
        accumulator = accumulator - request.getValue();
        rp.processResponse(new Integer(accumulator));
    }

    public void multiply(Multiply request, ResponseProcessor rp) throws Exception {
        accumulator = accumulator * request.getValue();
        rp.processResponse(new Integer(accumulator));
    }

    public void divide(Divide request, ResponseProcessor rp) throws Exception {
        accumulator = accumulator / request.getValue();
        rp.processResponse(new Integer(accumulator));
    }
}
