package org.agilewiki.jid.scalar.vlens.bytes;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a BytesJidA.
 */
public class BytesJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public BytesJidFactory() {
        super(JidFactories.BYTES_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected BytesJid instantiateActor()
            throws Exception {
        return new BytesJid();
    }
}
