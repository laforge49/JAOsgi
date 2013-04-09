package org.agilewiki.jid.factory.timing;

import org.agilewiki.incdes.AppBase;
import org.agilewiki.incdes.AppFactory;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class A extends AppBase {
    public static A create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (A) FactoryLocatorImpl.newJid(actor, PAFactories.BYTES_JID_TYPE, mailbox, parent);
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
