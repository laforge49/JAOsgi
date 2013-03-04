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
package org.agilewiki.jid.jaosgi;

import org.agilewiki.jactor.MailboxFactory;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public final class Activator implements BundleActivator {
    private final Logger logger = LoggerFactory.getLogger(Activator.class);
    private static final String CONFIG_PID = "JAOsgi";
    private JABCOsgiImpl jaBundleContext;

    public void start(BundleContext bundleContext) throws Exception {
        JAServiceTracker jaServiceTracker = new JAServiceTracker(bundleContext);
        jaServiceTracker.open(true);

        ServiceReference mailboxFactoryServiceReference = bundleContext.
                getServiceReference(MailboxFactory.class);
        if (mailboxFactoryServiceReference == null) {
            String msg = "Unable to get MailboxFactory ServiceReference";
            logger.error(msg);
            throw new IllegalStateException(msg);
        }
        MailboxFactory mailboxFactory = (MailboxFactory) jaServiceTracker.
                getService(mailboxFactoryServiceReference);
        if (mailboxFactory == null) {
            String msg = "getService on the MailboxFactory ServiceReference returned null";
            logger.error(msg);
            throw new IllegalStateException(msg);
        }

        jaBundleContext = new JABCOsgiImpl();
        jaBundleContext.setBundleContext(bundleContext);
        jaBundleContext.setJAServiceTracker(jaServiceTracker);
        jaBundleContext.initialize(mailboxFactory.createAsyncMailbox());

        JAFactoryLocator factoryLocator = new JAFactoryLocator();
        factoryLocator.initialize(mailboxFactory.createAsyncMailbox(), jaBundleContext);
        JidFactories jidFactories = new JidFactories();
        jidFactories.initialize();
        jidFactories.configure(factoryLocator);
    }

    public void stop(BundleContext context) {
        Iterator<ServiceRegistration> srit = jaBundleContext.getServiceRegistrations().iterator();
        while (srit.hasNext())
            srit.next().unregister();
    }
}
