/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.util;

import static org.seasar.doma.internal.util.AssertionUtil.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author taedium
 * 
 */
public class CompositeIterator<E> implements Iterator<E> {

    protected final List<Iterator<? extends E>> iterators;

    protected Iterator<? extends E> removableIterator;

    public CompositeIterator(List<Iterator<? extends E>> iterators) {
        assertNotNull(iterators);
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        Iterator<? extends E> iterator = getNextIndex();
        if (iterator == null) {
            return false;
        }
        return iterator.hasNext();
    }

    @Override
    public E next() {
        Iterator<? extends E> iterator = getNextIndex();
        if (iterator == null) {
            throw new NoSuchElementException();
        }
        removableIterator = iterator;
        for (Iterator<Iterator<? extends E>> it = iterators.iterator(); it
                .hasNext();) {
            if (it.next() == iterator) {
                break;
            }
            it.remove();
        }
        return iterator.next();
    }

    @Override
    public void remove() {
        if (removableIterator == null) {
            throw new IllegalStateException();
        }
        removableIterator.remove();
    }

    protected Iterator<? extends E> getNextIndex() {
        for (Iterator<? extends E> iterator : iterators) {
            if (iterator.hasNext()) {
                return iterator;
            }
        }
        return null;
    }
}
