package org.agilewiki.jid.scalar.flens.lng;

import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.JidFactories;

/**
 * Creates a LongJidA.
 */
public class LongJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public LongJidFactory() {
        super(JidFactories.LONG_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected LongJid instantiateActor()
            throws Exception {
        return new LongJid();
    }
}
