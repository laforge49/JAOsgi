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
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.IncDesImpl;

/**
 * Holds a map with Integer keys.
 */
public class IntegerSMap<VALUE_TYPE extends IncDesImpl> extends SMap<Integer, VALUE_TYPE> {
    /**
     * Returns the IncDesFactory for the key.
     *
     * @return The IncDesFactory for the key.
     */
    final protected Factory getKeyFactory() throws Exception {
        return Util.getActorFactory(this, IncDesFactories.PAINTEGER);
    }

    /**
     * Converts a string to a key.
     *
     * @param skey The integer to be converted.
     * @return The key.
     */
    final protected Integer stringToKey(String skey) {
        return new Integer(skey);
    }
}
