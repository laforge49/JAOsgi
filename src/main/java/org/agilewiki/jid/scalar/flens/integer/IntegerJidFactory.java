package org.agilewiki.jid.scalar.flens.integer;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a IntegerJidA.
 */
public class IntegerJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new IntegerJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected IntegerJidFactory() {
        super(JidFactories.INTEGER_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected IntegerJid instantiateActor()
            throws Exception {
        return new IntegerJid();
    }
}
