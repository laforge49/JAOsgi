package org.agilewiki.jid.basics;

import org.agilewiki.jactor.old.RP;
import org.agilewiki.jid.collection.flenc.TupleJid;
import org.agilewiki.jid.collection.flenc.TupleJidFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;
import org.agilewiki.jid.scalar.vlens.StringJid;

public class User extends TupleJid implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new UserFactory("user"));
    }

    private static class UserFactory extends TupleJidFactory {
        public UserFactory(String actorType) {
            super(actorType, JidFactories.STRING_JID_TYPE, JidFactories.INTEGER_JID_TYPE, JidFactories.STRING_JID_TYPE);
        }

        @Override
        protected User instantiateActor() throws Exception {
            User user = new User();
            return user;
        }
    }

    @Override
    public void processRequest(Proc request, RP rp) throws Exception {
        StringJid name = (StringJid) iGet(0);
        System.out.println("name: " + name.getValue());
        IntegerJid age = (IntegerJid) iGet(1);
        System.out.println("age: " + age.getValue());
        StringJid location = (StringJid) iGet(2);
        System.out.println("location: " + location.getValue());
        rp.processResponse(null);
    }
}
