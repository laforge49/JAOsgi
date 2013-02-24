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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ManagedService;

import java.util.*;

public final class Activator implements BundleActivator {
    private static final String CONFIG_PID = "JAOsgi";
    private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();
    private ConfigUpdater configUpdater;
    private BundleContext bundleContext;
    private JAOsgiContext jaOsgiContext;

    public BundleContext getBundleContext() {
        return bundleContext;
    }

    public void start(BundleContext bundleContext) {
        jaOsgiContext = new JAOsgiContext();
        jaOsgiContext.setActivator(this);
        this.bundleContext = bundleContext;
        JAServiceTracker jaServiceTracker = new JAServiceTracker(bundleContext);
        jaServiceTracker.open(true);
        jaOsgiContext.setJAServiceTracker(jaServiceTracker);
        ConfigUpdater configUpdater = new ConfigUpdater(jaOsgiContext);
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put(Constants.SERVICE_PID, CONFIG_PID);
        registerService(
                ManagedService.class.getName(),
                configUpdater,
                properties);
    }

    public void stop(BundleContext context) {
        configUpdater.stop();
        Iterator<ServiceRegistration> srit = registrations.iterator();
        while (srit.hasNext())
            srit.next().unregister();
    }

    public ServiceRegistration registerService(String clazz, Object service, Dictionary properties) {
        ServiceRegistration serviceRegistration = bundleContext.registerService(clazz, service, properties);
        registrations.add(serviceRegistration);
        return serviceRegistration;
    }
}
