/*
 * Copyright 2013 Bill La Forge
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
package org.agilewiki.jid.manifest;

import org.agilewiki.jid.collection.flenc.TupleJid;
import org.agilewiki.jid.collection.flenc.TupleJidFactory;
import org.agilewiki.jid.collection.vlenc.map.MapEntry;
import org.agilewiki.jid.collection.vlenc.map.MapEntryFactory;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.collection.vlenc.map.StringMapJidFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

public class ManifestJid extends StringMapJid<TupleJid> {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        TupleJidFactory.registerFactory(
                factoryLocator,
                "T." + JidFactories.MANIFEST_TYPE,
                JidFactories.INTEGER_JID_TYPE,
                JidFactories.STRING_JID_TYPE);

        MapEntryFactory.registerFactory(
                factoryLocator,
                "E." + JidFactories.MANIFEST_TYPE,
                JidFactories.STRING_JID_TYPE,
                "T." + JidFactories.MANIFEST_TYPE);

        factoryLocator.registerActorFactory(new StringMapJidFactory(
                JidFactories.MANIFEST_TYPE, "T." + JidFactories.MANIFEST_TYPE, 1) {
            @Override
            protected ManifestJid instantiateActor()
                    throws Exception {
                return new ManifestJid();
            }
        });
    }

    /**
     * Returns true if first usage.
     */
    public boolean inc(String locatorKey, String location) throws Exception {
        boolean rv = kMake(locatorKey);
        TupleJid tj = kGet(locatorKey);
        StringJid sj = (StringJid) tj.iGet(1);
        sj.setValue(location);
        IntegerJid ij = (IntegerJid) tj.iGet(0);
        int i = ij.getValue();
        ij.setValue(i + 1);
        return i == 0;
    }

    /**
     * Returns true if no more usages
     */
    public boolean dec(String locatorKey) throws Exception {
        boolean rv = kMake(locatorKey);
        TupleJid tj = kGet(locatorKey);
        IntegerJid ij = (IntegerJid) tj.iGet(0);
        int i = ij.getValue() - 1;
        if (i > -1)
            ij.setValue(i);
        return i == 0;
    }

    public void addAll(ManifestJid mj) throws Exception {
        if (mj == null)
            return;
        int s = mj.size();
        int i = 0;
        while (i < s) {
            MapEntry<String, TupleJid> me = mj.iGet(i);
            String locatorKey = me.getKey();
            TupleJid mt = me.getValue();
            StringJid locationJid = (StringJid) mt.iGet(1);
            inc(locatorKey, locationJid.getValue());
            i += 1;
        }
    }
}
