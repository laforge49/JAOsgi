package org.agilewiki.jid.basics;

import org.agilewiki.jactor.RP;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;

public class LuckyNumber extends IntegerJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineActorType("lucky number", LuckyNumber.class);
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        System.out.println("Your lucky number is " + getValue());
        rp.processResponse(null);
    }
}
