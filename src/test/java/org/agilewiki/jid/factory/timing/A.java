package org.agilewiki.jid.factory.timing;

import org.agilewiki.jid.collection.flenc.AppJid;
import org.agilewiki.jid.collection.flenc.AppJidFactory;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class A extends AppJid {
    public static A create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (A) JAFactoryLocator.newJid(actor, JidFactories.BYTES_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new AppJidFactory("A") {
            @Override
            final protected A instantiateActor()
                    throws Exception {
                return new A();
            }
        });
    }
}
