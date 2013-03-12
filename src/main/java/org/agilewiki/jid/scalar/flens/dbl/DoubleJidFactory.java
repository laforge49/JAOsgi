package org.agilewiki.jid.scalar.flens.dbl;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a DoubleJidA.
 */
public class DoubleJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new DoubleJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected DoubleJidFactory() {
        super(JidFactories.DOUBLE_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected DoubleJid instantiateActor() throws Exception {
        return new DoubleJid();
    }
}
