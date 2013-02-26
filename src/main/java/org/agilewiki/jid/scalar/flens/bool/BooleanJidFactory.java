package org.agilewiki.jid.scalar.flens.bool;

import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.JidFactories;

/**
 * Creates a BooleanJidA.
 */
public class BooleanJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public BooleanJidFactory() {
        super(JidFactories.BOOLEAN_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected BooleanJid instantiateActor() throws Exception {
        return new BooleanJid();
    }
}
