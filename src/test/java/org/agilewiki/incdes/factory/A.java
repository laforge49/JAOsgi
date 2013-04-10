package org.agilewiki.incdes.factory;

import org.agilewiki.incdes.AppBase;
import org.agilewiki.incdes.AppFactory;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.Util;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

public class A extends AppBase {
    public static A create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (A) Util.newJid(actor, "A", mailbox, parent);
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
