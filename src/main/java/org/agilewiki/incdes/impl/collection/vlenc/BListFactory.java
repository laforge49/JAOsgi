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
package org.agilewiki.incdes.impl.collection.vlenc;

import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.impl.scalar.vlens.UnionImpl;
import org.agilewiki.incdes.impl.factory.ActorFactory;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates ListJids.
 */
public class BListFactory extends ActorFactory {
    private final static int NODE_CAPACITY = 28;

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String entryType)
            throws Exception {
        UnionImpl.registerFactory(factoryLocator,
                "U." + actorType, "LL." + actorType, "IL." + actorType);

        factoryLocator.registerJidFactory(new BListFactory(
                actorType, entryType, true, true));
        factoryLocator.registerJidFactory(new BListFactory(
                "IN." + actorType, entryType, false, false));

        SListFactory.registerFactory(factoryLocator,
                "LL." + actorType, entryType, NODE_CAPACITY);
        SListFactory.registerFactory(factoryLocator,
                "IL." + actorType, "IN." + actorType, NODE_CAPACITY);
    }

    private String entryType;
    private boolean isRoot = true;
    private boolean auto = true;

    private BListFactory(String actorType, String entryType,
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
    protected BList instantiateActor() throws Exception {
        return new BList();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public BList newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        BList lj = (BList) super.newActor(mailbox, parent);
        FactoryLocator f = FactoryLocatorImpl.get(parent);
        lj.entryFactory = f.getJidFactory(entryType);
        lj.nodeCapacity = NODE_CAPACITY;
        lj.isRoot = isRoot;
        lj.init();
        if (auto)
            lj.setNodeLeaf();
        return lj;
    }
}
