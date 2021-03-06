/**
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.odata4j.producer.jdbc;

import org.odata4j.command.Command;
import org.odata4j.command.CommandContext;
import org.odata4j.command.CommandResult;

public class NoopCommand implements Command<CommandContext> {

  @Override
  public CommandResult execute(CommandContext context) throws Exception {
    return CommandResult.CONTINUE;
  }

}