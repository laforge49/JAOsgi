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
package org.agilewiki.incdes.impl.collection.vlenc.map;

import org.agilewiki.incdes.Factory;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.factory.FactoryImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates map entries.
 */
public class MapEntryFactory extends FactoryImpl {

    public static void registerFactory(FactoryLocator factoryLocator,
                                       String actorType,
                                       String keyType,
                                       String valueType)
            throws Exception {
        factoryLocator.registerJidFactory(new MapEntryFactory(
                actorType, keyType, valueType));
    }

    private String keyType;
    private String valueType;

    /**
     * Create an FactoryImpl.
     *
     * @param jidType The jid type.
     */
    protected MapEntryFactory(String jidType, String keyType, String valueType) {
        super(jidType);
        this.keyType = keyType;
        this.valueType = valueType;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    @Override
    protected MapEntryImpl instantiateActor() throws Exception {
        return new MapEntryImpl();
    }

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    public MapEntryImpl newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        MapEntryImpl me = (MapEntryImpl) super.newActor(mailbox, parent);
        FactoryLocator fl = Util.getFactoryLocator(mailbox);
        Factory keyFactory = fl.getFactory(keyType);
        Factory valueFactory = fl.getFactory(valueType);
        me.setFactories(keyFactory, valueFactory);
        return me;
    }
}
