package org.agilewiki.jid.basics;

import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.impl.IncDesImpl;
import org.agilewiki.jactor.ResponseProcessor;

public class HelloWorld extends IncDesImpl implements Main {
    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineJidType("hi", HelloWorld.class);
    }

    @Override
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        System.out.println("Hello world!");
        rp.processResponse(null);
    }
}
