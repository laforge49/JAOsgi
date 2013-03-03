package org.agilewiki.jid.basics;

import org.agilewiki.jactor.RP;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid._Jid;
import org.agilewiki.jid.collection.vlenc.ListJid;
import org.agilewiki.jid.collection.vlenc.ListJidFactory;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;

import java.util.Iterator;

public class Sum extends ListJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerActorFactory(new SumFactory("sum"));
    }

    private static class SumFactory extends ListJidFactory {
        public SumFactory(String actorType) {
            super(actorType, JidFactories.INTEGER_JID_TYPE);
        }

        @Override
        protected Sum instantiateActor()
                throws Exception {
            Sum sum = new Sum();
            return sum;
        }
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        initializeList();
        int sum = 0;
        Iterator<_Jid> it = list.iterator();
        while (it.hasNext()) {
            IntegerJid ij = (IntegerJid) it.next();
            sum += ij.getValue();
        }
        System.out.println("Total: " + sum);
        rp.processResponse(null);
    }
}
