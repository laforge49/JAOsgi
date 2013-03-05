package org.agilewiki.jid.scalar.flens.bool;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a BooleanJidA.
 */
public class BooleanJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new BooleanJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected BooleanJidFactory() {
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
