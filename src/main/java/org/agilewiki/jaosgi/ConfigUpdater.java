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
package org.agilewiki.jaosgi;

import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.MailboxFactory;
import org.agilewiki.jactor.factory.JAFactoryLocator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public final class ConfigUpdater implements ManagedService {
    final Logger logger = LoggerFactory.getLogger(ConfigUpdater.class);
    private List<ServiceRegistration> registrations;
    private int threadCount = 0;
    private BundleContext context;
    private MailboxFactory mailboxFactory;

    public ConfigUpdater(List<ServiceRegistration> registrations, BundleContext context) {
        this.registrations = registrations;
        this.context = context;
    }

    @Override
    public void updated(Dictionary config) throws ConfigurationException {
        if (config == null)
            return;
        if (threadCount > 0)
            throw new ConfigurationException("threadCount", "no changes allowed while JAMailboxFactory is running");
        String tc = (String) config.get("threadCount");
        try {
            threadCount = Integer.valueOf(tc);
        } catch(Exception ex) {
            throw new ConfigurationException("threadCount", "not an int: "+tc, ex);
        }
        logger.info("threadCount: " + threadCount);
        mailboxFactory = JAMailboxFactory.newMailboxFactory(threadCount);
        registrations.add(context.registerService(
                MailboxFactory.class.getName(),
                mailboxFactory,
                new Hashtable<String, Object>()));

        JAFactoryLocator factoryLocator = new JAFactoryLocator();
        try {
            factoryLocator.initialize(mailboxFactory.createMailbox());
        } catch (Exception e) {
            throw new ConfigurationException("threadCount", "unable to initialize JAFactoryLocator", e);
        }
        JidFactories jidFactories = new JidFactories();
        try {
            jidFactories.initialize();
            jidFactories.configure(factoryLocator);
        } catch (Exception e) {
            throw new ConfigurationException("threadCount", "unable to initialize JidFactories", e);
        }
        registrations.add(context.registerService(
                JidFactories.class.getName(),
                jidFactories,
                new Hashtable<String, Object>()));
    }

    public void stop() {
        mailboxFactory.close();
    }
}
