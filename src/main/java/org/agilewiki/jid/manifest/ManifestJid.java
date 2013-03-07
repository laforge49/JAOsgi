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

import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.collection.vlenc.map.StringMapJidFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

public class ManifestJid extends StringMapJid<ManifestTupleJid> {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        ManifestTupleJid.registerFactory(
                factoryLocator,
                "T." + JidFactories.MANIFEST_TYPE,
                JidFactories.MANIFEST_INTEGER_TYPE,
                JidFactories.MANIFEST_STRING_TYPE);

        ManifestMapEntry.registerFactory(
                factoryLocator,
                "E." + JidFactories.MANIFEST_TYPE,
                JidFactories.MANIFEST_STRING_TYPE,
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

    @Override
    public ManifestJid getManifestJid() throws Exception {
        return null;
    }

    /**
     * Returns true if first usage.
     */
    public boolean inc(String locatorKey, String location) throws Exception {
        boolean rv = kMake(locatorKey);
        ManifestTupleJid tj = kGet(locatorKey);
        ManifestStringJid sj = (ManifestStringJid) tj.iGet(1);
        sj.setValue(location);
        ManifestIntegerJid ij = (ManifestIntegerJid) tj.iGet(0);
        int i = ij.getValue();
        ij.setValue(i + 1);
        return i == 0;
    }

    /**
     * Returns true if no more usages
     */
    public boolean dec(String locatorKey) throws Exception {
        boolean rv = kMake(locatorKey);
        ManifestTupleJid tj = kGet(locatorKey);
        ManifestIntegerJid ij = (ManifestIntegerJid) tj.iGet(0);
        int i = ij.getValue() - 1;
        if (i > -1)
            ij.setValue(i);
        return i == 0;
    }
}
