package org.agilewiki.jid.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.incdes.impl.collection.flenc.TupleFactory;
import org.agilewiki.incdes.impl.collection.flenc.TupleImpl;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;

public class User extends TupleImpl implements Main {

    public static void register(FactoryLocator factoryLocator) throws Exception {
        factoryLocator.registerJidFactory(new UserFactory("user"));
    }

    private static class UserFactory extends TupleFactory {
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
    public void processRequest(Proc request, ResponseProcessor rp) throws Exception {
        PAStringImpl name = (PAStringImpl) iGet(0);
        System.out.println("name: " + name.getValue());
        PAIntegerImpl age = (PAIntegerImpl) iGet(1);
        System.out.println("age: " + age.getValue());
        PAStringImpl location = (PAStringImpl) iGet(2);
        System.out.println("location: " + location.getValue());
        rp.processResponse(null);
    }
}
