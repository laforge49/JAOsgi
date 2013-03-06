package org.agilewiki.jid.scalar.flens.flt;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a FloatJidA.
 */
public class FloatJidFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerActorFactory(new FloatJidFactory());
    }

    /**
     * Create a JLPCActorFactory.
     */
    protected FloatJidFactory() {
        super(JidFactories.FLOAT_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected FloatJid instantiateActor()
            throws Exception {
        return new FloatJid();
    }
}
