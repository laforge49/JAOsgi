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
import java.util.Collection;
import java.util.Dictionary;

public class JAOsgiContext extends JLPCActor implements BundleContext {
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

    @Override
    public Object getService(ServiceReference reference) {
        return getBundleContext().getService(reference);
    }

    @Override
    public File getDataFile(String filename) {
        return getBundleContext().getDataFile(filename);
    }

    @Override
    public Filter createFilter(String filter) throws InvalidSyntaxException {
        return getBundleContext().createFilter(filter);
    }

    @Override
    public Bundle getBundle(String location) {
        return getBundleContext().getBundle(location);
    }

    public ServiceReference getServiceReference(Class clazz) {
        return jaServiceTracker.getServiceReference(clazz);
    }

    @Override
    public String getProperty(String key) {
        return getBundleContext().getProperty(key);
    }

    @Override
    public Bundle getBundle() {
        return getBundleContext().getBundle();
    }

    @Override
    public Bundle installBundle(String location, InputStream input) throws BundleException {
        return getBundleContext().installBundle(location, input);
    }

    @Override
    public Bundle installBundle(String location) throws BundleException {
        return getBundleContext().installBundle(location);
    }

    @Override
    public Bundle getBundle(long id) {
        return getBundleContext().getBundle(id);
    }

    @Override
    public Bundle[] getBundles() {
        return new Bundle[0];
    }

    @Override
    public void addServiceListener(ServiceListener listener, String filter) throws InvalidSyntaxException {
        getBundleContext().addServiceListener(listener, filter);
    }

    @Override
    public void addServiceListener(ServiceListener listener) {
        getBundleContext().addServiceListener(listener);
    }

    @Override
    public void removeServiceListener(ServiceListener listener) {
        getBundleContext().removeServiceListener(listener);
    }

    @Override
    public void addBundleListener(BundleListener listener) {
        getBundleContext().addBundleListener(listener);
    }

    @Override
    public void removeBundleListener(BundleListener listener) {
        getBundleContext().removeBundleListener(listener);
    }

    @Override
    public void addFrameworkListener(FrameworkListener listener) {
        getBundleContext().addFrameworkListener(listener);
    }

    @Override
    public void removeFrameworkListener(FrameworkListener listener) {
        getBundleContext().removeFrameworkListener(listener);
    }

    @Override
    public ServiceRegistration registerService(String[] clazzes, Object service, Dictionary properties) {
        return getBundleContext().registerService(clazzes, service, properties);
    }

    @Override
    public ServiceRegistration registerService(Class clazz, Object service, Dictionary properties) {
        return getBundleContext().registerService(clazz, service, properties);
    }

    public ServiceReference[] getServiceReferences(String clazz, String filter)
            throws InvalidSyntaxException {
        return jaServiceTracker.getServiceReferences(clazz, filter);
    }

    @Override
    public ServiceReference[] getAllServiceReferences(String clazz, String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];  //To change body of implemented methods use File | Settings | File Templates.
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
