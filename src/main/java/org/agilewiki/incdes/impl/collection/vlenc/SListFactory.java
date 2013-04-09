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

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates ListJids.
 */
public class SListFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String valueType)
            throws Exception {
        registerFactory(factoryLocator, actorType, valueType, 10);
    }

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String valueType,
                                       int initialCapacity)
            throws Exception {
        factoryLocator.registerJidFactory(new SListFactory(
                actorType, valueType, initialCapacity));
    }

    private String entryType;
    private int initialCapacity;

    /**
     * Create an ActorFactory.
     *
     * @param jidType         The jid type.
     * @param entryType       The entry type.
     * @param initialCapacity The initial capacity.
     */
    protected SListFactory(String jidType, String entryType, int initialCapacity) {
        super(jidType);
        this.entryType = entryType;
        this.initialCapacity = initialCapacity;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    protected SList instantiateActor() throws Exception {
        return new SList();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public SList newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        SList lj = (SList) super.newActor(mailbox, parent);
        FactoryLocator fl = JAFactoryLocator.get(parent);
        lj.entryFactory = fl.getJidFactory(entryType);
        lj.initialCapacity = initialCapacity;
        return lj;
    }
}
