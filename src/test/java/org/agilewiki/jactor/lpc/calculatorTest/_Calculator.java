package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.TargetActor;

public interface _Calculator extends TargetActor {
    public void clear(Clear request, ResponseProcessor rp) throws Exception;

    public void get(Get request, ResponseProcessor rp) throws Exception;

    public void set(Set request, ResponseProcessor rp) throws Exception;

    public void add(Add request, ResponseProcessor rp) throws Exception;

    public void subtract(Subtract request, ResponseProcessor rp) throws Exception;

    public void multiply(Multiply request, ResponseProcessor rp) throws Exception;

    public void divide(Divide request, ResponseProcessor rp) throws Exception;
}
