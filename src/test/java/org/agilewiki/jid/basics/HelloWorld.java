package org.agilewiki.jid.basics;

import org.agilewiki.jactor.RP;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jid.Jid;

public class HelloWorld extends Jid implements Main {
    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineActorType("hi", HelloWorld.class);
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        System.out.println("Hello world!");
        rp.processResponse(null);
    }
}
