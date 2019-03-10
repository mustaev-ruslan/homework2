package ru.aaxee.homework2.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singleton;


public class MathSet<E> {

    private HashSet<E> internalSet;
    private boolean isAll;

    public MathSet(Collection<E> collection) {
        internalSet = new HashSet<>(collection);
        isAll = false;
    }

    private MathSet(boolean isAll) {
        this.isAll = isAll;
    }

    public static <E> MathSet<E> all() {
        return new MathSet<>(true);
    }

    public void nothing() {
        internalSet = new HashSet<>();
        isAll = false;
    }

    public void filter(Collection<E> collection) {
        if (isAll) {
            internalSet = new HashSet<>(collection);
            isAll = false;
        } else {
            internalSet.retainAll(collection);
        }
    }

    public void filter(E object) {
        if (isAll) {
            internalSet = new HashSet<>(singleton(object));
            isAll = false;
        } else {
            internalSet.retainAll(singleton(object));
        }
    }

    public boolean notEmpty() {
        if (isAll) {
            return true;
        } else {
            return !internalSet.isEmpty();
        }
    }

    public boolean isAll() {
        return isAll;
    }

    public Set<E> toSet() {
        if (isAll) {
            throw new IllegalStateException("Cannot convert math set of all to java set");
        }
        return new HashSet<>(internalSet);
    }
}
