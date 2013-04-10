package org.agilewiki.incdes.factory.timing;

import org.agilewiki.incdes.*;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class A extends AppBase {
    public static A create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (A) Util.newJid(actor, IncDesFactories.BYTES_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new AppFactory("A") {
            @Override
            final protected A instantiateActor()
                    throws Exception {
                return new A();
            }
        });
    }
}
