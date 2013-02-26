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
import org.osgi.framework.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

public class JABCNoOsgiImpl extends JABundleContext {
    public static FactoryLocator createNoOsgiFactoryLocator(int threadCount) throws Exception {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(threadCount);
        JABCNoOsgiImpl jaBundleContext = new JABCNoOsgiImpl();
        jaBundleContext.initialize(mailboxFactory.createMailbox());
        JAFactoryLocator factoryLocator = new JAFactoryLocator();
        factoryLocator.initialize(mailboxFactory.createMailbox(), jaBundleContext);
        return factoryLocator;
    }

    @Override
    public List<ServiceRegistration> getServiceRegistrations() {
        return new ArrayList<ServiceRegistration>();
    }

    @Override
    public ServiceRegistration registerService(String clazz, Object service, Dictionary properties) {
        return null;
    }

    @Override
    public ServiceReference getServiceReference(String clazz) {
        return null;
    }

    @Override
    public Object getService(ServiceReference reference) {
        return null;
    }

    @Override
    public File getDataFile(String filename) {
        return null;
    }

    @Override
    public Filter createFilter(String filter) throws InvalidSyntaxException {
        return null;
    }

    @Override
    public Bundle getBundle(String location) {
        return null;
    }

    @Override
    public ServiceReference getServiceReference(Class clazz) {
        return null;
    }

    @Override
    public String getProperty(String key) {
        return null;
    }

    @Override
    public Bundle getBundle() {
        return null;
    }

    @Override
    public Bundle installBundle(String location, InputStream input) throws BundleException {
        return null;
    }

    @Override
    public Bundle installBundle(String location) throws BundleException {
        return null;
    }

    @Override
    public Bundle getBundle(long id) {
        return null;
    }

    @Override
    public Bundle[] getBundles() {
        return new Bundle[0];
    }

    @Override
    public void addServiceListener(ServiceListener listener, String filter) throws InvalidSyntaxException {
    }

    @Override
    public void addServiceListener(ServiceListener listener) {
    }

    @Override
    public void removeServiceListener(ServiceListener listener) {
    }

    @Override
    public void addBundleListener(BundleListener listener) {
    }

    @Override
    public void removeBundleListener(BundleListener listener) {
    }

    @Override
    public void addFrameworkListener(FrameworkListener listener) {
    }

    @Override
    public void removeFrameworkListener(FrameworkListener listener) {
    }

    @Override
    public ServiceRegistration registerService(String[] clazzes, Object service, Dictionary properties) {
        return null;
    }

    @Override
    public ServiceRegistration registerService(Class clazz, Object service, Dictionary properties) {
        return null;
    }

    @Override
    public ServiceReference[] getServiceReferences(String clazz, String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];
    }

    @Override
    public ServiceReference[] getAllServiceReferences(String clazz, String filter) throws InvalidSyntaxException {
        return new ServiceReference[0];
    }

    @Override
    public Collection<ServiceReference> getServiceReferences(Class clazz, String filter) throws InvalidSyntaxException {
        return new ArrayList<ServiceReference>();
    }

    @Override
    public boolean ungetService(ServiceReference serviceReference) {
        return false;
    }

    @Override
    public void stop(int options) throws BundleException {
        getMailboxFactory().close();
    }
}
