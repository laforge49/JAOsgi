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
package org.agilewiki.incdes;

import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;
import org.agilewiki.pautil.Ancestor;
import org.agilewiki.pautil.AncestorBase;
import org.osgi.framework.*;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

abstract public class Context extends AncestorBase implements Actor {
    public static Context get(final Ancestor actor)
            throws Exception {
        Context bundleContext = (Context) AncestorBase.
                getMatch(actor, Context.class);
        if (bundleContext == null)
            throw new IllegalStateException("Context is not an ancestor of " + actor);
        return bundleContext;
    }

    /**
     * The actor's mailbox.
     */
    private Mailbox mailbox;

    public MailboxFactory getMailboxFactory() {
        return getMailbox().getMailboxFactory();
    }

    abstract public List<ServiceRegistration> getServiceRegistrations();

    abstract public ServiceRegistration registerService(String clazz, Object service, Dictionary properties);

    abstract public ServiceReference getServiceReference(String clazz);

    abstract public Object getService(ServiceReference reference);

    abstract public File getDataFile(String filename);

    abstract public Filter createFilter(String filter) throws InvalidSyntaxException;

    abstract public Bundle getBundle(String location);

    abstract public ServiceReference getServiceReference(Class clazz);

    abstract public String getProperty(String key);

    abstract public Bundle getBundle();

    abstract public Bundle installBundle(String location, InputStream input) throws BundleException;

    abstract public Bundle installBundle(String location) throws BundleException;

    abstract public Bundle getBundle(long id);

    abstract public Bundle[] getBundles();

    abstract public void addServiceListener(ServiceListener listener, String filter) throws InvalidSyntaxException;

    abstract public void addServiceListener(ServiceListener listener);

    abstract public void removeServiceListener(ServiceListener listener);

    abstract public void addBundleListener(BundleListener listener);

    abstract public void removeBundleListener(BundleListener listener);

    abstract public void addFrameworkListener(FrameworkListener listener);

    abstract public void removeFrameworkListener(FrameworkListener listener);

    abstract public ServiceRegistration registerService(String[] clazzes, Object service, Dictionary properties);

    abstract public ServiceRegistration registerService(Class clazz, Object service, Dictionary properties);

    abstract public ServiceReference[] getServiceReferences(String clazz, String filter)
            throws InvalidSyntaxException;

    abstract public ServiceReference[] getAllServiceReferences(String clazz, String filter) throws InvalidSyntaxException;

    abstract public Collection<ServiceReference> getServiceReferences(Class clazz, String filter)
            throws InvalidSyntaxException;

    abstract public boolean ungetService(ServiceReference serviceReference);

    abstract public void stop(int options) throws Exception;

    public void initialize(final Mailbox _mailbox, final Ancestor _parent)
            throws Exception {
        super.initialize(_parent);
        mailbox = _mailbox;
    }

    public void initialize(final Mailbox _mailbox)
            throws Exception {
        super.initialize();
        mailbox = _mailbox;
    }

    @Override
    public Mailbox getMailbox() {
        return mailbox;
    }

    @Override
    public boolean sameMailbox(final Actor other) {
        return mailbox == other.getMailbox();
    }
}
