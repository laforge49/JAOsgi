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
package org.agilewiki.jaosgi;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.factory.ActorFactory;
import org.agilewiki.jactor.factory._ActorFactory;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jid.JidFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * An actor for defining actor types and creating instances.
 */
public class JAFactoryLocator extends JLPCActor implements FactoryLocator{
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

    /**
     * A table which maps type names to actor factories.
     */
    private ConcurrentSkipListMap<String, ActorFactory> types = new ConcurrentSkipListMap<String, ActorFactory>();

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
        ActorFactory af = types.get(actorType);
        if (af == null) {
            FactoryLocator a = (FactoryLocator) getAncestor(FactoryLocator.class);
            if (a != null)
                return a.newActor(actorType, mailbox, parent);
            throw new IllegalArgumentException("Unknown actor type: " + actorType);
        }
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
        ActorFactory af = types.get(actorType);
        if (af == null) {
            FactoryLocator a = (FactoryLocator) getAncestor(FactoryLocator.class);
            if (a != null)
                return a.getActorFactory(actorType);
            throw new IllegalArgumentException("Unknown actor type: " + actorType);
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
        if (types.containsKey(actorType))
            throw new IllegalArgumentException("Actor type is already defined: " + actorType);
        if (Actor.class.isAssignableFrom(clazz)) {
            Constructor componentConstructor = clazz.getConstructor();
            ActorFactory actorFactory = new _ActorFactory(actorType, componentConstructor);
            types.put(actorType, actorFactory);
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
        ActorFactory old = types.get(actorType);
        if (old == null) {
            types.put(actorType, actorFactory);
            registerAsService(actorFactory);
        }
        else if (!old.equals(actorFactory))
            throw new IllegalArgumentException("Actor type is already defined: " + actorType);
    }

    private void registerAsService(ActorFactory actorFactory) throws Exception {
        if (!(actorFactory instanceof JidFactory))
            return;
        JAOsgiContext jaOsgiContext = JAOsgiContext.getJAOsgiContext(this);
        BundleContext bundleContext = jaOsgiContext.getBundleContext();
        Bundle bundle = bundleContext.getBundle();
        String bundleName = bundle.getSymbolicName();
        Version version = bundle.getVersion();
        String location = bundle.getLocation();
        actorFactory.setDescriptor(bundleName, version, location);
        String factoryKey = actorFactory.getFactoryKey();
        Hashtable<String, String> dict = new Hashtable();
        dict.put("FACTORY_KEY", factoryKey);
        jaOsgiContext.registerService(ActorFactory.class.getName(), actorFactory, dict);
    }
}
