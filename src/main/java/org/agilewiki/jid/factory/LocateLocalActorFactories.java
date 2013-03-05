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
package org.agilewiki.jid.factory;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.osgi.framework.Bundle;

import java.util.Hashtable;

public abstract class LocateLocalActorFactories extends JLPCActor {
    private JAFactoryLocator factoryLocator;

    protected JAFactoryLocator configure(String name) throws Exception {
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(this);

        factoryLocator = new JAFactoryLocator();
        factoryLocator.initialize(getMailboxFactory().createAsyncMailbox(), jaBundleContext);

        Bundle bundle = jaBundleContext.getBundle();
        if (bundle == null) {
            factoryLocator.configure(name);
            return factoryLocator; //no OSGi
        }
        factoryLocator.configure(bundle);
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put("LOCATOR_KEY", factoryLocator.getLocatorKey());
        jaBundleContext.registerService(
                LocateLocalActorFactories.class.getName(),
                this,
                properties);
        return factoryLocator;
    }

    public ActorFactory _getActorFactory(String actorType)
            throws Exception {
        return factoryLocator._getActorFactory(actorType);
    }
}
