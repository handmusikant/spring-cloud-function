/*
 * Copyright 2018-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.adapter.aws;

import java.util.function.Supplier;

import org.springframework.cloud.function.web.source.DestinationResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class LambdaDestinationResolver implements DestinationResolver {

	@Override
	public String destination(Supplier<?> supplier, String name, Object value) {
		Message<?> message = (Message<?>) value;
		MessageHeaders headers = message.getHeaders();
		if (headers.containsKey("lambda-runtime-aws-request-id")) {
			return (String) headers.get("lambda-runtime-aws-request-id");
		}
		return "unknown";
	}

}
