/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.odata4j.producer.inmemory;

import java.util.HashMap;

import org.core4j.Func;
import org.core4j.Func1;

public class InMemoryEntityInfo<TEntity> {

  String entitySetName;
  String entityTypeName;
  String[] keys;
  Class<TEntity> entityClass;
  Func<Iterable<TEntity>> get;
  Func1<Object, HashMap<String, Object>> id;
  PropertyModel properties;
  boolean hasStream;
}
