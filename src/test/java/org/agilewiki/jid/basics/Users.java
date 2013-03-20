package org.agilewiki.jid.basics;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jid._Jid;
import org.agilewiki.jid.collection.vlenc.map.MapEntry;
import org.agilewiki.jid.collection.vlenc.map.MapEntryFactory;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

import java.util.Iterator;

public class Users extends StringMapJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new UsersFactory("users"));
        MapEntryFactory.registerFactory(factoryLocator,
                "E.users", JidFactories.STRING_JID_TYPE, JidFactories.STRING_JID_TYPE);
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
            users.valueFactory = fl.getJidFactory(JidFactories.STRING_JID_TYPE);
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
