/*
 * Copyright 2012 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.jid.collection.vlenc;

import org.agilewiki.jactor.Ancestor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.scalar.vlens.actor.UnionJidFactory;

/**
 * Creates ListJids.
 */
public class BListJidFactory extends ActorFactory {
    private final static int NODE_CAPACITY = 28;

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String entryType)
            throws Exception {
        UnionJidFactory.registerFactory(factoryLocator,
                "U." + actorType, "LL." + actorType, "IL." + actorType);

        factoryLocator.registerJidFactory(new BListJidFactory(
                actorType, entryType, true, true));
        factoryLocator.registerJidFactory(new BListJidFactory(
                "IN." + actorType, entryType, false, false));

        ListJidFactory.registerFactory(factoryLocator,
                "LL." + actorType, entryType, NODE_CAPACITY);
        ListJidFactory.registerFactory(factoryLocator,
                "IL." + actorType, "IN." + actorType, NODE_CAPACITY);
    }

    private String entryType;
    private boolean isRoot = true;
    private boolean auto = true;

    private BListJidFactory(String actorType, String entryType,
                            boolean isRoot, boolean auto) {
        super(actorType);
        this.entryType = entryType;
        this.isRoot = isRoot;
        this.auto = auto;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    protected JLPCActor instantiateActor() throws Exception {
        return new BListJid();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public BListJid newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        BListJid lj = (BListJid) super.newActor(mailbox, parent);
        FactoryLocator f = JAFactoryLocator.get(parent);
        lj.entryFactory = f.getJidFactory(entryType);
        lj.nodeCapacity = NODE_CAPACITY;
        lj.isRoot = isRoot;
        lj.init();
        if (auto)
            lj.setNodeLeaf();
        return lj;
    }
}
