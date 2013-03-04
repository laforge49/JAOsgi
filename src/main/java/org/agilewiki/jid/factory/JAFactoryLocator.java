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
package org.agilewiki.jid.factory;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.osgi.framework.startlevel.FrameworkStartLevel;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * An actor for defining actor types and creating instances.
 */
public class JAFactoryLocator extends JLPCActor implements FactoryLocator {
    public static String versionString(Version version) {
        return "" + version.getMajor() + "." + version.getMajor();
    }

    /**
     * Returns the requested actor factory.
     *
     * @param actor     The actor which is the factory or which has a factory as an ancestor.
     * @param actorType The actor type.
     * @return The registered actor factory.
     */
    public static ActorFactory getActorFactory(Actor actor, String actorType)
            throws Exception {
        if (!(actor instanceof FactoryLocator))
            actor = actor.getAncestor(FactoryLocator.class);
        if (actor == null)
            throw new IllegalArgumentException("Unknown actor type: " + actorType);
        FactoryLocator factoryLocator = (FactoryLocator) actor;
        return factoryLocator.getActorFactory(actorType);
    }

    public static FactoryLocator getFactoryLocator(Actor actor)
            throws Exception {
        if (!(actor instanceof FactoryLocator))
            actor = actor.getAncestor(FactoryLocator.class);
        if (actor == null)
            throw new IllegalArgumentException("not an ancestor: FactoryLocator");
        return (FactoryLocator) actor;
    }

    /**
     * Creates a new actor.
     *
     * @param actor     The actor which is the factory or which has a factory as an ancestor.
     * @param actorType The actor type.
     * @return The new actor.
     */
    public static Actor newActor(Actor actor, String actorType)
            throws Exception {
        return newActor(actor, actorType, null, null);
    }

    /**
     * Creates a new actor.
     *
     * @param actor     The actor which is the factory or which has a factory as an ancestor.
     * @param actorType The actor type.
     * @param mailbox   A mailbox which may be shared with other actors, or null.
     * @return The new actor.
     */
    public static Actor newActor(Actor actor, String actorType, Mailbox mailbox)
            throws Exception {
        return newActor(actor, actorType, mailbox, null);
    }

    /**
     * Creates a new actor.
     *
     * @param actor     The actor which is the factory or which has a factory as an ancestor.
     * @param actorType The actor type.
     * @param mailbox   A mailbox which may be shared with other actors, or null.
     * @param parent    The parent actor to which unrecognized requests are forwarded, or null.
     * @return The new actor.
     */
    public static Actor newActor(Actor actor, String actorType, Mailbox mailbox, Actor parent)
            throws Exception {
        if (!(actor instanceof FactoryLocator))
            actor = actor.getAncestor(FactoryLocator.class);
        if (actor == null)
            return null;
        FactoryLocator factoryLocator = (FactoryLocator) actor;
        return factoryLocator.newActor(actorType, mailbox, parent);
    }

    private ArrayList<LocateLocalActorFactories> factoryImports = new ArrayList();

    /**
     * A table which maps type names to actor factories.
     */
    private ConcurrentSkipListMap<String, ActorFactory> types = new ConcurrentSkipListMap<String, ActorFactory>();

    public void importFactories(LocateLocalActorFactories locateLocalActorFactories) {
        factoryImports.add(locateLocalActorFactories);
    }

    /**
     * Creates a new actor.
     *
     * @param actorType The actor type.
     * @return The new actor.
     */
    public Actor newActor(String actorType)
            throws Exception {
        return newActor(actorType, null, null);
    }

    /**
     * Creates a new actor.
     *
     * @param actorType The actor type.
     * @param mailbox   A mailbox which may be shared with other actors, or null.
     * @return The new actor.
     */
    public Actor newActor(String actorType, Mailbox mailbox)
            throws Exception {
        return newActor(actorType, mailbox, null);
    }

    /**
     * Creates a new actor.
     *
     * @param actorType The actor type.
     * @param mailbox   A mailbox which may be shared with other actors, or null.
     * @param parent    The parent actor to which unrecognized requests are forwarded, or null.
     * @return The new actor.
     */
    public Actor newActor(String actorType, Mailbox mailbox, Actor parent)
            throws Exception {
        if (mailbox == null || parent == null) {
            if (mailbox == null) mailbox = getMailbox();
            if (parent == null) parent = this;
        }
        ActorFactory af = getActorFactory(actorType);
        return af.newActor(mailbox, parent);
    }

    /**
     * Returns the requested actor factory.
     *
     * @param actorType The actor type.
     * @return The registered actor factory.
     */
    @Override
    public ActorFactory getActorFactory(String actorType)
            throws Exception {
        ActorFactory af = _getActorFactory(actorType);
        if (af == null) {
            throw new IllegalArgumentException("Unknown actor type: " + actorType);
        }
        return af;
    }

    @Override
    public ActorFactory _getActorFactory(String actorType)
            throws Exception {
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(this);
        String factoryKey = null;
        if (actorType.contains("|")) {
            int i = actorType.lastIndexOf('|');
            factoryKey = actorType.substring(0, i);
        } else {
            Bundle bundle = jaBundleContext.getBundle();
            if (bundle == null)
                factoryKey = actorType + "||";
            else {
                String bundleName = bundle.getSymbolicName();
                Version version = bundle.getVersion();
                factoryKey = actorType + "|" + bundleName + "|" + versionString(version);
            }
        }
        ActorFactory af = types.get(factoryKey);
        if (af == null) {
            Iterator<LocateLocalActorFactories> it = factoryImports.iterator();
            while (it.hasNext()) {
                af = it.next()._getActorFactory(actorType);
                if (af != null)
                    return af;
            }
            if (!actorType.contains("|"))
                throw new IllegalArgumentException("Unknown actor type: " + factoryKey);
            int i = actorType.lastIndexOf('|');
            String location = actorType.substring(i + 1);
            Collection<ServiceReference> serviceReferences = jaBundleContext.
                    getServiceReferences(ActorFactory.class, "FACTORY_KEY=" + factoryKey);
            if (!serviceReferences.isEmpty()) {
                ServiceReference serviceReference = serviceReferences.iterator().next();
                af = (ActorFactory) jaBundleContext.getService(serviceReference);
            } else {
                Bundle bundle = null;
                try {
                    bundle = jaBundleContext.installBundle(location);
                } catch (BundleException be) {
                    if (be.getType() != BundleException.DUPLICATE_BUNDLE_ERROR)
                        throw new IllegalArgumentException("Unknown actor type: " + factoryKey, be);
                }
                if (bundle == null)
                    throw new IllegalArgumentException("Unknown actor type: " + factoryKey);
                Bundle systemBundle = jaBundleContext.getBundle(0);
                int activeStartLevel = systemBundle.adapt(FrameworkStartLevel.class).getStartLevel();
                int bundleStartLevel = bundle.adapt(BundleStartLevel.class).getStartLevel();
                if (bundleStartLevel > activeStartLevel)
                    throw new IllegalStateException("bundle start level is too high");
                bundle.start(); //will not work if starting level is too high
                Collection<ServiceReference> srs = jaBundleContext.
                        getServiceReferences(ActorFactory.class, "FACTORY_KEY=" + factoryKey);
                if (srs.isEmpty())
                    throw new IllegalArgumentException("Unknown actor type: " + factoryKey);
                ServiceReference serviceReference = srs.iterator().next();
                af = (ActorFactory) jaBundleContext.getService(serviceReference);
            }
        }
        return af;
    }

    /**
     * Bind an actor type to a Class.
     *
     * @param actorType The actor type.
     * @param clazz     The class of the actor.
     */
    @Override
    public void defineActorType(String actorType, Class clazz)
            throws Exception {
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(this);
        Bundle bundle = jaBundleContext.getBundle();
        String factoryKey = actorType + "||";
        if (bundle != null) {
            String bundleName = bundle.getSymbolicName();
            Version version = bundle.getVersion();
            factoryKey = actorType + "|" + bundleName + "|" + versionString(version);
        }
        if (types.containsKey(factoryKey))
            throw new IllegalArgumentException("Actor type is already defined: " + actorType);
        if (Actor.class.isAssignableFrom(clazz)) {
            Constructor componentConstructor = clazz.getConstructor();
            ActorFactory actorFactory = new _ActorFactory(actorType, componentConstructor);
            types.put(factoryKey, actorFactory);
            registerAsService(actorFactory);
            return;
        }
        throw new IllegalArgumentException(clazz.getName());
    }

    /**
     * Register an actor factory.
     *
     * @param actorFactory An actor factory.
     */
    @Override
    public void registerActorFactory(ActorFactory actorFactory)
            throws Exception {
        String actorType = actorFactory.actorType;
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(this);
        Bundle bundle = jaBundleContext.getBundle();
        String factoryKey = actorType + "||";
        if (bundle != null) {
            String bundleName = bundle.getSymbolicName();
            Version version = bundle.getVersion();
            factoryKey = actorType + "|" + bundleName + "|" + versionString(version);
        }
        ActorFactory old = types.get(factoryKey);
        if (old == null) {
            types.put(factoryKey, actorFactory);
            registerAsService(actorFactory);
        } else if (!old.equals(actorFactory))
            throw new IllegalArgumentException("Actor type is already defined: " + actorType);
    }

    private void registerAsService(ActorFactory actorFactory) throws Exception {
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(this);
        Bundle bundle = jaBundleContext.getBundle();
        if (bundle == null) {
            actorFactory.setDescriptor("", "", "");
        } else {
            String bundleName = bundle.getSymbolicName();
            Version version = bundle.getVersion();
            String location = bundle.getLocation();
            actorFactory.setDescriptor(bundleName, versionString(version), location);
        }
        String factoryKey = actorFactory.getFactoryKey();
        Hashtable<String, String> dict = new Hashtable();
        dict.put("FACTORY_KEY", factoryKey);
        jaBundleContext.registerService(ActorFactory.class.getName(), actorFactory, dict);
    }
}
