package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.JidFactories;

/**
 * Creates a RootJid.
 */
public class RootJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public RootJidFactory() {
        super(JidFactories.ROOT_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected RootJid instantiateActor()
            throws Exception {
        return new RootJid();
    }
}
