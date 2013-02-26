package org.agilewiki.jid.scalar.flens.dbl;

import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jaosgi.JidFactories;

/**
 * Creates a DoubleJidA.
 */
public class DoubleJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public DoubleJidFactory() {
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
