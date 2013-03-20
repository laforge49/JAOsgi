package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * Creates a RootJid.
 */
public class RootJidFactory extends ActorFactory {
    public static RootJid create(Actor actor, Mailbox mailbox, Actor parent) throws Exception {
        return (RootJid) JAFactoryLocator.newJid(actor, JidFactories.ROOT_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new RootJidFactory());
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
