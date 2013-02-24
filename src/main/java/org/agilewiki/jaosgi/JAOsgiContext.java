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

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.osgi.framework.*;

import java.util.Collection;
import java.util.Dictionary;

public class JAOsgiContext extends JLPCActor {
    public static JAOsgiContext getJAOsgiContext(final Actor actor)
            throws Exception {
        Actor a = actor;
        if (!(a instanceof JAOsgiContext))
            a = a.getAncestor(JAOsgiContext.class);
        if (a == null)
            throw new IllegalStateException("JAOsgiContext is not an ancestor of " + actor);
        return (JAOsgiContext) a;
    }

    private Activator activator;
    private JAServiceTracker jaServiceTracker;

    public void setActivator(Activator activator) {
        if (this.activator != null)
            throw new IllegalStateException("duplicate activator");
        this.activator = activator;
    }

    public void setJAServiceTracker(JAServiceTracker jaServiceTracker) {
        if (this.jaServiceTracker != null)
            throw new IllegalStateException("duplicate jaServiceTracker");
        this.jaServiceTracker = jaServiceTracker;
    }

    public BundleContext getBundleContext() {
        return activator.getBundleContext();
    }

    public ServiceRegistration registerService(String clazz, Object service, Dictionary properties) {
        return activator.registerService(clazz, service, properties);
    }

    public ConfigUpdater getConfigUpdater() {
        return activator.getConfigUpdater();
    }

    public ServiceReference getServiceReference(String clazz) {
        return jaServiceTracker.getServiceReference(clazz);
    }

    public ServiceReference getServiceReference(Class clazz) {
        return jaServiceTracker.getServiceReference(clazz);
    }

    public ServiceReference[] getServiceReferences(String clazz, String filter)
            throws InvalidSyntaxException {
        return jaServiceTracker.getServiceReferences(clazz, filter);
    }

    public Collection<ServiceReference> getServiceReferences(Class clazz, String filter)
            throws InvalidSyntaxException {
        return jaServiceTracker.getServiceReferences(clazz, filter);
    }

    public boolean ungetService(ServiceReference serviceReference) {
        return jaServiceTracker.ungetService(serviceReference);
    }

    public FactoryLocator getFactoryLocator() {
        return getConfigUpdater().getFactoryLocator();
    }

    public void stop(int options) throws BundleException {
        getBundleContext().getBundle().stop(options);
    }
}
