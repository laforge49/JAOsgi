package org.agilewiki.jid.basics;

import org.agilewiki.incdes.IncDes;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.incdes.impl.collection.vlenc.map.StringSMap;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.scalar.vlens.UnionImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

import java.util.Iterator;

public class Users extends StringSMap implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new UsersFactory("users"));
        UnionImpl.registerFactory(factoryLocator,
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
        public Users newActor(Mailbox mailbox, Ancestor parent) throws Exception {
            Users users = (Users) super.newActor(mailbox, parent);
            FactoryLocator fl = JAFactoryLocator.get(parent);
            users.valueFactory = fl.getJidFactory(JidFactories.STRING_JID_TYPE);
            return users;
        }
    }

    @Override
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        initializeList();
        Iterator<IncDes> it = list.iterator();
        while (it.hasNext()) {
            MapEntryImpl<String, PAStringImpl> tj = (MapEntryImpl) it.next();
            String name = tj.getKey();
            PAStringImpl email = (PAStringImpl) tj.getValue();
            System.out.println("name: " + name + ", email: " + email.getValue());
        }
        rp.processResponse(null);
    }
}
