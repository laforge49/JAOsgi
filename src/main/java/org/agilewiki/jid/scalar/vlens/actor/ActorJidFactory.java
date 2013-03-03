package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jaosgi.JidFactories;

/**
 * Creates a ActorJid.
 */
public class ActorJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public ActorJidFactory() {
        super(JidFactories.ACTOR_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected ActorJid instantiateActor()
            throws Exception {
        return new ActorJid();
    }
}
