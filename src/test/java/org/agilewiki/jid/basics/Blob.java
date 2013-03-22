package org.agilewiki.jid.basics;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jid.collection.vlenc.map.MapEntryFactory;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.vlens.actor.ActorJid;

public class Blob extends StringMapJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new BlobFactory("blob"));
        MapEntryFactory.registerFactory(factoryLocator,
                "E.blob", JidFactories.STRING_JID_TYPE, JidFactories.ACTOR_JID_TYPE);
    }

    private static class BlobFactory extends ActorFactory {
        public BlobFactory(String actorType) {
            super(actorType);
        }

        @Override
        protected Blob instantiateActor()
                throws Exception {
            return new Blob();
        }

        @Override
        public Blob newActor(Mailbox mailbox, Actor parent) throws Exception {
            Blob blob = (Blob) super.newActor(mailbox, parent);
            FactoryLocator fl = JAFactoryLocator.get(parent);
            blob.valueFactory = fl.getJidFactory(JidFactories.ACTOR_JID_TYPE);
            return blob;
        }
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        initializeList();
        ActorJid aj = (ActorJid) kGet("fun");
        Actor a = aj.getValue();
        Proc.req.send(this, a, rp);
    }
}
