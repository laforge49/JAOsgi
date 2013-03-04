package org.agilewiki.jid.scalar.vlens.string;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a StringJid.
 */
public class StringJidFactory extends ActorFactory {
    /**
     * Create a JLPCActorFactory.
     */
    public StringJidFactory() {
        super(JidFactories.STRING_JID_TYPE);
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    final protected StringJid instantiateActor()
            throws Exception {
        return new StringJid();
    }
}
