package org.agilewiki.jid.basics;

import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.impl.collection.vlenc.map.StringSMap;
import org.agilewiki.incdes.impl.factory.FactoryImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.incdes.impl.scalar.vlens.UnionImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class Blob extends StringSMap implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new BlobFactory("blob"));
        UnionImpl.registerFactory(factoryLocator,
                "E.blob", IncDesFactories.PASTRING, IncDesFactories.BOX);
    }

    private static class BlobFactory extends FactoryImpl {
        public BlobFactory(String actorType) {
            super(actorType);
        }

        @Override
        protected Blob instantiateActor()
                throws Exception {
            return new Blob();
        }

        @Override
        public Blob newActor(Mailbox mailbox, Ancestor parent) throws Exception {
            Blob blob = (Blob) super.newActor(mailbox, parent);
            FactoryLocator fl = FactoryLocatorImpl.getFactoryLocator(parent);
            blob.valueFactory = fl.getFactory(IncDesFactories.BOX);
            return blob;
        }
    }

    @Override
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        initializeList();
        BoxImpl aj = (BoxImpl) kGet("fun");
        Actor a = aj.getValue();
        Proc.req.send(this, a, rp);
    }
}
