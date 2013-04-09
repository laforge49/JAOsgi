package org.agilewiki.jid.basics;

import org.agilewiki.incdes.IncDes;
import org.agilewiki.incdes.impl.collection.vlenc.SListFactory;
import org.agilewiki.incdes.impl.collection.vlenc.SList;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.pactor.ResponseProcessor;

import java.util.Iterator;

public class Sum extends SList implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new SumFactory("sum"));
    }

    private static class SumFactory extends SListFactory {
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
        Iterator<IncDes> it = list.iterator();
        while (it.hasNext()) {
            PAIntegerImpl ij = (PAIntegerImpl) it.next();
            sum += ij.getValue();
        }
        System.out.println("Total: " + sum);
        rp.processResponse(null);
    }
}
