package org.agilewiki.incdes;

import org.agilewiki.incdes.impl.factory.FactoryImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates AppBase objects.
 */
abstract public class AppFactory extends FactoryImpl {
    private Factory[] tupleFactories;
    private String[] jidTypes;

    public AppFactory(String subActorType) {
        super(subActorType);
        this.jidTypes = new String[0];
    }

    /**
     * Create a JLPCActorFactory.
     *
     * @param subJidType The jid type.
     * @param jidTypes   The element types.
     */
    public AppFactory(String subJidType, String... jidTypes) {
        super(subJidType);
        this.jidTypes = jidTypes;
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public AppBase newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        AppBase tj = (AppBase) super.newActor(mailbox, parent);
        FactoryLocator fl = Util.getFactoryLocator(parent);
        Factory[] afs = new FactoryImpl[jidTypes.length];
        int i = 0;
        while (i < jidTypes.length) {
            afs[i] = fl.getFactory(jidTypes[i]);
            i += 1;
        }
        tupleFactories = afs;
        tj.tupleFactories = tupleFactories;
        return tj;
    }
}
