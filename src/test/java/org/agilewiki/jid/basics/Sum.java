package org.agilewiki.jid.basics;

import org.agilewiki.incdes.PAIncDes;
import org.agilewiki.jid.collection.vlenc.ListJid;
import org.agilewiki.jid.collection.vlenc.ListJidFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.flens.IntegerJid;
import org.agilewiki.pactor.ResponseProcessor;

import java.util.Iterator;

public class Sum extends ListJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new SumFactory("sum"));
    }

    private static class SumFactory extends ListJidFactory {
        public SumFactory(String actorType) {
            super(actorType, JidFactories.INTEGER_JID_TYPE, 10);
        }

        @Override
        protected Sum instantiateActor()
                throws Exception {
            Sum sum = new Sum();
            return sum;
        }
    }

    @Override
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        initializeList();
        int sum = 0;
        Iterator<PAIncDes> it = list.iterator();
        while (it.hasNext()) {
            IntegerJid ij = (IntegerJid) it.next();
            sum += ij.getValue();
        }
        System.out.println("Total: " + sum);
        rp.processResponse(null);
    }
}
