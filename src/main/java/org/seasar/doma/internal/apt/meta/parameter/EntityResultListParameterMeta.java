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
package org.seasar.doma.internal.apt.meta.parameter;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import org.seasar.doma.internal.apt.cttype.EntityCtType;

/**
 * @author taedium
 * 
 */
public class EntityResultListParameterMeta implements ResultListParameterMeta {

    private final EntityCtType entityCtType;

    private final boolean ensureResultMapping;

    public EntityResultListParameterMeta(EntityCtType entityCtType, boolean ensureResultMapping) {
        assertNotNull(entityCtType);
        this.entityCtType = entityCtType;
        this.ensureResultMapping = ensureResultMapping;
    }

    public EntityCtType getEntityCtType() {
        return entityCtType;
    }

    public boolean getEnsureResultMapping() {
        return ensureResultMapping;
    }

    @Override
    public <R, P> R accept(CallableSqlParameterMetaVisitor<R, P> visitor, P p) {
        return visitor.visitEntityResultListParameterMeta(this, p);
    }

}