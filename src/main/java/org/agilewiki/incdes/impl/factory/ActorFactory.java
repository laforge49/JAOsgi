/*
 * Copyright 2011 Bill La Forge
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
package org.agilewiki.incdes.impl.factory;

import org.agilewiki.incdes.Factory;
import org.agilewiki.incdes.impl.IncDesImpl;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * Creates a JLPCActor.
 */
abstract public class ActorFactory implements Factory {
    private String factoryKey;

    /**
     * The jid type.
     */
    public final String jidType;
    private FactoryLocatorImpl factoryLocator;

    @Override
    public String getType() {
        return jidType;
    }

    public void configure(FactoryLocatorImpl factoryLocator) {
        this.factoryLocator = factoryLocator;
    }

    public String getFactoryKey() {
        if (factoryKey == null)
            factoryKey = jidType + "|" + getLocatorKey();
        return factoryKey;
    }

    public String getLocatorKey() {
        return factoryLocator.getLocatorKey();
    }

    public String getLocation() {
        return factoryLocator.getLocation();
    }

    /**
     * Create an ActorFactory.
     *
     * @param jidType The jid type.
     */
    public ActorFactory(String jidType) {
        this.jidType = jidType;
    }

    /**
     * Create a JLPCActor.
     *
     * @return The new actor.
     */
    abstract protected IncDesImpl instantiateActor()
            throws Exception;

    /**
     * Create and configure an actor.
     *
     * @param mailbox The mailbox of the new actor.
     * @param parent  The parent of the new actor.
     * @return The new actor.
     */
    @Override
    public IncDesImpl newActor(Mailbox mailbox, Ancestor parent)
            throws Exception {
        IncDesImpl a = instantiateActor();
        a.initialize(mailbox, parent, this);
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ActorFactory))
            return false;
        ActorFactory af = (ActorFactory) o;
        return getFactoryKey().equals(af.getFactoryKey());
    }
}
