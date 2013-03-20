package org.agilewiki.jid.basics;

import org.agilewiki.jactor.old.RP;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;

public class LuckyNumber extends IntegerJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.defineJidType("lucky number", LuckyNumber.class);
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        System.out.println("Your lucky number is " + getValue());
        rp.processResponse(null);
    }
}
