package org.agilewiki.jid.basics;

import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.collection.flenc.TupleJidFactory;

public class UserFactory extends TupleJidFactory {
    public UserFactory(String actorType) {
        super(actorType, JidFactories.STRING_JID_TYPE, JidFactories.INTEGER_JID_TYPE, JidFactories.STRING_JID_TYPE);
    }

    @Override
    protected User instantiateActor() throws Exception {
        User user = new User();
        return user;
    }
}
