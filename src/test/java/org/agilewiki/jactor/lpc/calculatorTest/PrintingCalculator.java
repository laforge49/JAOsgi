package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.ExceptionHandler;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Actor;

/**
 * Test code.
 */
public class PrintingCalculator extends JLPCActor implements _Calculator {
    private Calculator calculator;

    @Override
    public void initialize(final Mailbox mailbox, final Actor parent)
            throws Exception {
        super.initialize(mailbox, parent);
        calculator = new Calculator();
        calculator.initialize(mailbox, parent);
    }

    @Override
    public void clear(final Clear request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("Clear => " + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("Clear => " + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void get(final Get request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("Get => " + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("Get => " + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void set(final Set request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("Set " + request.getValue() + " => "
                        + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("Set " + request.getValue() + " => "
                        + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void add(final Add request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("+ " + request.getValue() + " => "
                        + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("+ " + request.getValue() + " => "
                        + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void subtract(final Subtract request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("- " + request.getValue() + " => "
                        + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("- " + request.getValue() + " => "
                        + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void multiply(final Multiply request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("* " + request.getValue() + " => "
                        + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("* " + request.getValue() + " => "
                        + response);
                rp.processResponse(response);
            }
        });
    }

    @Override
    public void divide(final Divide request, final ResponseProcessor rp) throws Exception {
        setExceptionHandler(new ExceptionHandler() {
            @Override
            public void process(final Throwable exception) throws Exception {
                System.out.println("/ " + request.getValue() + " => "
                        + exception);
                rp.processResponse(null);
            }
        });
        send(calculator, request, new ResponseProcessor() {
            @Override
            public void processResponse(final Object response) throws Exception {
                System.out.println("/ " + request.getValue() + " => "
                        + response);
                rp.processResponse(response);
            }
        });
    }
}
