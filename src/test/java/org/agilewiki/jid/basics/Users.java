package org.agilewiki.jid.basics;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.RP;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid._Jid;
import org.agilewiki.jid.collection.vlenc.map.MapEntry;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

import java.util.Iterator;

public class Users extends StringMapJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerActorFactory(new UsersFactory("users"));
    }

    private static class UsersFactory extends ActorFactory {
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

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        initializeList();
        Iterator<_Jid> it = list.iterator();
        while (it.hasNext()) {
            MapEntry<String, StringJid> tj = (MapEntry) it.next();
            String name = tj.getKey();
            StringJid email = (StringJid) tj.getValue();
            System.out.println("name: " + name + ", email: " + email.getValue());
        }
        rp.processResponse(null);
    }
}
