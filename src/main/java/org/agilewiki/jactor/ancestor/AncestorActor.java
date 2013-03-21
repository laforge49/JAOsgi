package org.agilewiki.jactor.ancestor;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.old.Mailbox;

public class AncestorActor extends JLPCActor implements Ancestor {
    public static Ancestor getAncestor(final Ancestor child, final Class targetClass) {
        if (child == null)
            return null;
        return getMatch(child.getParent(), targetClass);
    }

    public static Ancestor getMatch(Ancestor ancestor, final Class targetClass) {
        while (ancestor != null) {
            if (targetClass.isInstance(ancestor))
                return ancestor;
            ancestor = ancestor.getParent();
        }
        return null;
    }

    /**
     * The parent actor, for dependency injection.
     */
    protected Ancestor parent;

    /**
     * Returns the actor's parent.
     *
     * @return The actor's parent, or null.
     */
    @Override
    final public Ancestor getParent() {
        return parent;
    }

    /**
     * Initialize a degraded LiteActor
     *
     * @param parent The parent actor.
     */
    public void initialize(final Ancestor parent) throws Exception {
        initialize(null, parent);
    }

    /**
     * Initialize a LiteActor
     *
     * @param mailbox A mailbox which may be shared with other actors.
     * @param parent  The parent actor.
     */
    public void initialize(final Mailbox mailbox, final Ancestor parent)
            throws Exception {
        if (this.mailbox != null || this.parent != null)
            throw new IllegalStateException("already initialized");
        this.mailbox = mailbox;
        this.parent = parent;
    }
}
