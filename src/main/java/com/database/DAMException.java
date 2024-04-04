package com.database;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DAMException extends Exception implements Iterable<Throwable>{
    public DAMException(String message) {
        super(message);
    }

    @Override
    public Iterator<Throwable> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Throwable> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Throwable> spliterator() {
        return Iterable.super.spliterator();
    }
}
