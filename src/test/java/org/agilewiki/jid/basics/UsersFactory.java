package org.agilewiki.jid.basics;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JidFactories;

public class UsersFactory extends ActorFactory {
    public UsersFactory(String actorType) {
        super(actorType);
    }

    @Override
    protected Users instantiateActor()
            throws Exception {
        return new Users();
    }

    @Override
    public Users newActor(Mailbox mailbox, Actor parent) throws Exception {
        Users users = (Users) super.newActor(mailbox, parent);
        FactoryLocator fl = (FactoryLocator) parent.getMatch(FactoryLocator.class);
        users.valueFactory = fl.getActorFactory(JidFactories.STRING_JID_TYPE);
        return users;
    }
}
