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
package org.agilewiki.incdes.impl.collection.flenc;

import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.factory.ActorFactory;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates a TupleImpl.
 */
public class TupleFactory extends ActorFactory {

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String subActorType, String... actorTypes) throws Exception {
        factoryLocator.registerJidFactory(new TupleFactory(subActorType, actorTypes));
    }

    private String[] jidTypes;

    /**
     * Create a JLPCActorFactory.
     *
     * @param subJidType The jid type.
     * @param jidTypes   The element types.
     */
    protected TupleFactory(String subJidType, String... jidTypes) {
        super(subJidType);
        this.jidTypes = jidTypes;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    protected TupleImpl instantiateActor()
            throws Exception {
        return new TupleImpl();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public TupleImpl newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        TupleImpl tj = (TupleImpl) super.newActor(mailbox, parent);
        FactoryLocator fl = Util.getFactoryLocator(parent);
        ActorFactory[] afs = new ActorFactory[jidTypes.length];
        int i = 0;
        while (i < jidTypes.length) {
            afs[i] = fl.getJidFactory(jidTypes[i]);
            i += 1;
        }
        tj.tupleFactories = afs;
        return tj;
    }
}
