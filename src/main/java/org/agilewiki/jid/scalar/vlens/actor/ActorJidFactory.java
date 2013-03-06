package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a ActorJid.
 */
public class ActorJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new ActorJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected ActorJidFactory() {
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
