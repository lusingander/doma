/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author nakamura-to
 *
 */
public final class IteratorUtil {

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public static <T> Iterator<T> copy(Iterator<T> iterator) {
        List<T> list = toList(iterator);
        return list.iterator();
    }
}