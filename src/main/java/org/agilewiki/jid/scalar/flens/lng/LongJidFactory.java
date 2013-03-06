package org.agilewiki.jid.scalar.flens.lng;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a LongJidA.
 */
public class LongJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new LongJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected LongJidFactory() {
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
