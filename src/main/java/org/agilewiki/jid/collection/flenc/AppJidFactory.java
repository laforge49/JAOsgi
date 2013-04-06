package org.agilewiki.jid.collection.flenc;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates AppJid objects.
 */
abstract public class AppJidFactory extends ActorFactory {
    private ActorFactory[] tupleFactories;
    private String[] jidTypes;

    public AppJidFactory(String subActorType) {
        super(subActorType);
        this.tupleFactories = new ActorFactory[0];
    }

    /**
     * Create a JLPCActorFactory.
     *
     * @param subJidType     The jid type.
     * @param tupleFactories The element factories.
     */
    public AppJidFactory(String subJidType, ActorFactory... tupleFactories) {
        super(subJidType);
        this.tupleFactories = tupleFactories;
    }

    /**
     * Create a JLPCActorFactory.
     *
     * @param subJidType The jid type.
     * @param jidTypes   The element types.
     */
    public AppJidFactory(String subJidType, String... jidTypes) {
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
    public AppJid newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        AppJid tj = (AppJid) super.newActor(mailbox, parent);
        if (tupleFactories == null) {
            FactoryLocator fl = JAFactoryLocator.get(parent);
            ActorFactory[] afs = new ActorFactory[jidTypes.length];
            int i = 0;
            while (i < jidTypes.length) {
                afs[i] = fl.getJidFactory(jidTypes[i]);
                i += 1;
            }
            tupleFactories = afs;
        }
        tj.tupleFactories = tupleFactories;
        return tj;
    }
}
