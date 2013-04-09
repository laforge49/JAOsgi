package org.agilewiki.jid.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;

public class LuckyNumber extends PAIntegerImpl implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineJidType("lucky number", LuckyNumber.class);
    }

    @Override
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        System.out.println("Your lucky number is " + getValue());
        rp.processResponse(null);
    }
}
