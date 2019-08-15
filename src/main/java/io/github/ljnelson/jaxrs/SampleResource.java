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

import javax.inject.Inject;
import javax.inject.Named;

import javax.sql.DataSource;

import javax.transaction.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.lra.annotation.Status;

import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;

@Path("sample")
public class SampleResource {

  @Inject
  @Named("sampleDataSource")
  private DataSource dataSource;
  
  public SampleResource() {
    super();
  }

  @GET
  @LRA(value = LRA.Type.MANDATORY)
  @Path("hello")
  @Produces("text/plain")
  @Transactional
  public String hello() {
    return "World";
  }
  
}
