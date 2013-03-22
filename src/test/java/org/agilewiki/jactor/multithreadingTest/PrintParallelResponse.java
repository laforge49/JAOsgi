package org.agilewiki.jactor.multithreadingTest;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class PrintParallelResponse<RESPONSE> extends RequestBase<Object, ParallelResponsePrinter> {
    private int count;
    private PrintResponse<RESPONSE> printResponse;
    private ResponsePrinter[] responsePrinters;

    public PrintResponse<RESPONSE> getPrintResponse() {
        return printResponse;
    }

    public int getCount() {
        return count;
    }

    public Actor[] getResponsePrinters() {
        return responsePrinters;
    }

    public PrintParallelResponse(int count,
                                 ResponsePrinter[] responsePrinters,
                                 PrintResponse<RESPONSE> printResponse) {
        this.count = count;
        this.responsePrinters = responsePrinters;

        this.printResponse = printResponse;
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof ParallelResponsePrinter;
    }

    @Override
    public void processRequest(JLPCActor targetActor, RP rp) throws Exception {
        ((ParallelResponsePrinter) targetActor).
                printParallelResponse(count, responsePrinters, printResponse, rp);
    }
}
