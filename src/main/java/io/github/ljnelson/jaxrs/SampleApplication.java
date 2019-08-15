/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2019 Laird Nelson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.github.ljnelson.jaxrs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

import io.narayana.lra.filter.FilterRegistration;

@ApplicationScoped
@ApplicationPath("/")
public class SampleApplication extends Application {

  private final Set<Class<?>> classes;
  
  public SampleApplication() {
    super();
    final Set<Class<?>> classes = new HashSet<>();
    classes.add(SampleResource.class);
    classes.add(FilterRegistration.class);
    this.classes = Collections.unmodifiableSet(classes);
  }

  @Override
  public Set<Class<?>> getClasses() {
    return this.classes;
  }
  
}
