/*
 * Tencent is pleased to support the open source community by making Spring Cloud Tencent available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */

package com.tencent.cloud.plugin.trace.config;

import com.tencent.cloud.plugin.trace.SpanAttributesProvider;
import com.tencent.cloud.plugin.trace.TraceClientMetadataEnhancedPlugin;
import com.tencent.cloud.plugin.trace.TraceServerMetadataEnhancedPlugin;
import com.tencent.cloud.polaris.context.ConditionalOnPolarisEnabled;
import com.tencent.cloud.polaris.context.PolarisSDKContextManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnPolarisEnabled
@ConditionalOnProperty(value = "spring.cloud.polaris.trace.enabled", matchIfMissing = true)
public class TraceEnhancedPluginAutoConfiguration {

	@Bean
	public TraceClientMetadataEnhancedPlugin traceClientMetadataEnhancedPlugin(
			PolarisSDKContextManager polarisSDKContextManager, @Autowired(required = false) SpanAttributesProvider spanAttributesProvider) {
		return new TraceClientMetadataEnhancedPlugin(polarisSDKContextManager, spanAttributesProvider);
	}

	@Bean
	public TraceServerMetadataEnhancedPlugin traceServerMetadataEnhancedPlugin(
			PolarisSDKContextManager polarisSDKContextManager, @Autowired(required = false) SpanAttributesProvider spanAttributesProvider) {
		return new TraceServerMetadataEnhancedPlugin(polarisSDKContextManager, spanAttributesProvider);
	}

}