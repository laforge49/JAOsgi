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
package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;

/**
 * Creates a UnionJid.
 */
public class UnionJidFactory extends ActorFactory {
    public static void registerFactory(FactoryLocator factoryLocator,
                                       String subActorType, String... actorTypes)
            throws Exception {
        factoryLocator.registerJidFactory(new UnionJidFactory(
                subActorType, actorTypes));
    }

    private ActorFactory[] unionFactories;
    private String[] actorTypes;

    /**
     * Create a JLPCActorFactory.
     *
     * @param subJidType The jid type.
     * @param actorTypes The element types.
     */
    protected UnionJidFactory(String subJidType, String... actorTypes) {
        super(subJidType);
        this.actorTypes = actorTypes;
    }

    /**
     * Initialize a new UnionJid with its element factories.
     *
     * @param tj The new union.
     */
    public void assignElementFactories(UnionJid tj) {
        tj.unionFactories = unionFactories;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    protected UnionJid instantiateActor()
            throws Exception {
        return new UnionJid();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public UnionJid newActor(Mailbox mailbox, Actor parent)
            throws Exception {
        UnionJid uj = (UnionJid) super.newActor(mailbox, parent);
        if (unionFactories == null) {
            FactoryLocator fl = JAFactoryLocator.getFactoryLocator(parent);
            ActorFactory[] afs = new ActorFactory[actorTypes.length];
            int i = 0;
            while (i < actorTypes.length) {
                afs[i] = fl.getJidFactory(actorTypes[i]);
                i += 1;
            }
            unionFactories = afs;
        }
        assignElementFactories(uj);
        return uj;
    }
}
