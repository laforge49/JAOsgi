package org.agilewiki.jid.basics;

import org.agilewiki.jactor.RP;
import org.agilewiki.jid.Jid;
import org.agilewiki.jid.factory.FactoryLocator;

public class HelloWorld extends Jid implements Main {
    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineJidType("hi", HelloWorld.class);
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        System.out.println("Hello world!");
        rp.processResponse(null);
    }
}
