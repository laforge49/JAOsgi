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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

public class JABundleContext extends JLPCActor {
    public static JABundleContext getJAOsgiContext(final Actor actor)
            throws Exception {
        Actor a = actor;
        if (!(a instanceof JABundleContext))
            a = a.getAncestor(JABundleContext.class);
        if (a == null)
            throw new IllegalStateException("JABundleContext is not an ancestor of " + actor);
        return (JABundleContext) a;
    }

    private BundleContext bundleContext;
    private JAServiceTracker jaServiceTracker;
    private FactoryLocator factoryLocator;
    private List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

    public List<ServiceRegistration> getServiceRegistrations() {
        return serviceRegistrations;
    }

    public void setBundleContext(BundleContext bundleContext) {
        if (this.bundleContext != null)
            throw new IllegalStateException("duplicate bundleContext");
        this.bundleContext = bundleContext;
    }

    public void setFactoryLocator(FactoryLocator factoryLocator) {
        if (this.bundleContext != null)
            throw new IllegalStateException("duplicate bundleContext");
        this.factoryLocator = factoryLocator;
    }

    public void setJAServiceTracker(JAServiceTracker jaServiceTracker) {
        if (this.jaServiceTracker != null)
            throw new IllegalStateException("duplicate jaServiceTracker");
        this.jaServiceTracker = jaServiceTracker;
    }

    public ServiceRegistration registerService(String clazz, Object service, Dictionary properties) {
        ServiceRegistration serviceRegistration = bundleContext.registerService(clazz, service, properties);
        serviceRegistrations.add(serviceRegistration);
        return serviceRegistration;
    }

    public ServiceReference getServiceReference(String clazz) {
        return bundleContext.getServiceReference(clazz);
    }

    public Object getService(ServiceReference reference) {
        return jaServiceTracker.getService(reference);
    }

    public File getDataFile(String filename) {
        return bundleContext.getDataFile(filename);
    }

    public Filter createFilter(String filter) throws InvalidSyntaxException {
        return bundleContext.createFilter(filter);
    }

    public Bundle getBundle(String location) {
        return bundleContext.getBundle(location);
    }

    public ServiceReference getServiceReference(Class clazz) {
        return bundleContext.getServiceReference(clazz);
    }

    public String getProperty(String key) {
        return bundleContext.getProperty(key);
    }

    public Bundle getBundle() {
        return bundleContext.getBundle();
    }

    public Bundle installBundle(String location, InputStream input) throws BundleException {
        return bundleContext.installBundle(location, input);
    }

    public Bundle installBundle(String location) throws BundleException {
        return bundleContext.installBundle(location);
    }

    public Bundle getBundle(long id) {
        return bundleContext.getBundle(id);
    }

    public Bundle[] getBundles() {
        return new Bundle[0];
    }

    public void addServiceListener(ServiceListener listener, String filter) throws InvalidSyntaxException {
        bundleContext.addServiceListener(listener, filter);
    }

    public void addServiceListener(ServiceListener listener) {
        bundleContext.addServiceListener(listener);
    }

    public void removeServiceListener(ServiceListener listener) {
        bundleContext.removeServiceListener(listener);
    }

    public void addBundleListener(BundleListener listener) {
        bundleContext.addBundleListener(listener);
    }

    public void removeBundleListener(BundleListener listener) {
        bundleContext.removeBundleListener(listener);
    }

    public void addFrameworkListener(FrameworkListener listener) {
        bundleContext.addFrameworkListener(listener);
    }

    public void removeFrameworkListener(FrameworkListener listener) {
        bundleContext.removeFrameworkListener(listener);
    }

    public ServiceRegistration registerService(String[] clazzes, Object service, Dictionary properties) {
        return bundleContext.registerService(clazzes, service, properties);
    }

    public ServiceRegistration registerService(Class clazz, Object service, Dictionary properties) {
        return bundleContext.registerService(clazz, service, properties);
    }

    public ServiceReference[] getServiceReferences(String clazz, String filter)
            throws InvalidSyntaxException {
        return bundleContext.getServiceReferences(clazz, filter);
    }

    public ServiceReference[] getAllServiceReferences(String clazz, String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<ServiceReference> getServiceReferences(Class clazz, String filter)
            throws InvalidSyntaxException {
        return bundleContext.getServiceReferences(clazz, filter);
    }

    public boolean ungetService(ServiceReference serviceReference) {
        return jaServiceTracker.ungetService(serviceReference);
    }

    public FactoryLocator getFactoryLocator() {
        return factoryLocator;
    }

    public void stop(int options) throws BundleException {
        bundleContext.getBundle().stop(options);
    }
}
