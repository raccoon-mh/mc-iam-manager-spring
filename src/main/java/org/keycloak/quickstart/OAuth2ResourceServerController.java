/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.quickstart;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class OAuth2ResourceServerController {

	@GetMapping("/**")
	public String index(HttpServletRequest request, @AuthenticationPrincipal Jwt jwt) {
		// System.out.println("Method : " + request.getMethod());
		System.out.println("URI : " + request.getRequestURI());
		System.out.println("preferred_username : " + jwt.getClaimAsString("preferred_username"));
		System.out.println("clientAddress : " + jwt.getClaimAsString("clientAddress"));
		return String.format("Hello, %s!", jwt.getClaimAsString("preferred_username"));
	}

	@PostMapping("/**")
	public String indexPost(HttpServletRequest request, @AuthenticationPrincipal Jwt jwt) {
		// System.out.println("Method : " + request.getMethod());
		System.out.println("URI : " + request.getRequestURI());
		System.out.println("preferred_username : " + jwt.getClaimAsString("preferred_username"));
		System.out.println("clientAddress : " + jwt.getClaimAsString("clientAddress"));
		return String.format("Hello, %s!", jwt.getClaimAsString("preferred_username"));
	}

}
