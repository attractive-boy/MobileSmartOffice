package org.springframework.security.oauth2.server.authorization.exception;

/*-
 * #%L
 * back
 * %%
 * Copyright (C) 2022 徐晓伟工作室
 * %%
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
 * #L%
 */

import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 飞书 redirectUri 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RedirectUriFeiShuWebPageException extends FeiShuWebPageException {

	public RedirectUriFeiShuWebPageException(String errorCode) {
		super(errorCode);
	}

	public RedirectUriFeiShuWebPageException(OAuth2Error error) {
		super(error);
	}

	public RedirectUriFeiShuWebPageException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public RedirectUriFeiShuWebPageException(OAuth2Error error, String message) {
		super(error, message);
	}

	public RedirectUriFeiShuWebPageException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

	@Override
	public OAuth2Error getError() {
		return super.getError();
	}

}
