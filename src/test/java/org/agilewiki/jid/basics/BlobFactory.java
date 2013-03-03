package org.agilewiki.jid.basics;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JidFactories;

public class BlobFactory extends ActorFactory {
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
        FactoryLocator fl = (FactoryLocator) parent.getMatch(FactoryLocator.class);
        blob.valueFactory = fl.getActorFactory(JidFactories.ACTOR_JID_TYPE);
        return blob;
    }
}
