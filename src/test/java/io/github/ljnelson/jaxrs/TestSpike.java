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

import javax.enterprise.context.ApplicationScoped;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import javax.enterprise.inject.spi.CDI;

import io.helidon.microprofile.server.Server;

import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApplicationScoped
class TestSpike {

  private Server server;

  TestSpike() {
    super();
  }

  @BeforeEach
  void startServer() {
    this.stopServer();
    final Server.Builder builder = Server.builder();
    assertNotNull(builder);
    // The Helidon MicroProfile server implementation uses
    // ConfigProviderResolver#getConfig(ClassLoader) directly
    // instead of ConfigProvider#getConfig() so we follow suit
    // here for fidelity.
    builder.config(ConfigProviderResolver.instance().getConfig(Thread.currentThread().getContextClassLoader()));
    final SeContainerInitializer initializer = SeContainerInitializer.newInstance()
      .addBeanClasses(TestSpike.class)
      .addBeanClasses(SampleApplication.class);
    assertNotNull(initializer);
    builder.cdiContainer(initializer.initialize());
    this.server = builder.build();
    assertNotNull(this.server);
    this.server.start();
  }
  
  @AfterEach
  void stopServer() {
    if (this.server != null) {
      this.server.stop();
      this.server = null;
    }
  }
  
  @Test
  final void testSpike() {
    
  }
  
}
