package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a RootJid.
 */
public class RootJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new RootJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected RootJidFactory() {
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
