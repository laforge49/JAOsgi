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
package org.agilewiki.jid.collection.vlenc.map;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jid.collection.vlenc.ListJidFactory;
import org.agilewiki.jid.scalar.vlens.actor.UnionJidFactory;

/**
 * Creates IntegerBMapJid's.
 */
public class IntegerBMapJidFactory extends ActorFactory {
    private final static int NODE_CAPACITY = 28;

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String valueType)
            throws Exception {
        factoryLocator.registerActorFactory(new UnionJidFactory(
                "U." + actorType, "LM." + actorType, "IM." + actorType));

        factoryLocator.registerActorFactory(new IntegerBMapJidFactory(
                actorType, valueType, true, true));
        factoryLocator.registerActorFactory(new IntegerBMapJidFactory(
                "IN." + actorType, valueType, false, false));

        factoryLocator.registerActorFactory(new ListJidFactory(
                "LM." + actorType, valueType, 28));
        factoryLocator.registerActorFactory(new ListJidFactory(
                "IM." + actorType, "IN." + actorType, NODE_CAPACITY));
    }

    private String valueType;
    private boolean isRoot = true;
    private boolean auto = true;

    /**
     * Create an ActorFactory.
     *
     * @param actorType    The actor type.
     * @param valueType    The value type.
     */
    public IntegerBMapJidFactory(String actorType, String valueType,
                                 boolean isRoot, boolean auto) {
        super(actorType);
        this.valueType = valueType;
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
        return new IntegerBMapJid();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public JLPCActor newActor(Mailbox mailbox, Actor parent)
            throws Exception {
        IntegerBMapJid imj = (IntegerBMapJid) super.newActor(mailbox, parent);
        FactoryLocator fl = (FactoryLocator) parent.getMatch(FactoryLocator.class);
        imj.valueFactory = fl.getActorFactory(valueType);
        imj.nodeCapacity = NODE_CAPACITY;
        imj.isRoot = isRoot;
        imj.init();
        if (auto)
            imj.setNodeLeaf();
        return imj;
    }
}
