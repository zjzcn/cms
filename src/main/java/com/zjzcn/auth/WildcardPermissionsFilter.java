package com.zjzcn.auth;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * Filter that allows access if the current user has the permissions specified
 * by the mapped value, or denies access if the user does not have all of the
 * permissions specified.
 * 
 * @since 0.9
 */
public class WildcardPermissionsFilter extends AuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI().split("[?]")[0];
		String[] strs = uri.split("[\\/\\.]");
		if (strs.length < 2) {
			return false;
		}
		String perm = strs[strs.length - 2];

		Subject subject = getSubject(request, response);
		if (subject.isPermitted(perm)) {
			return true;
		}

		return false;
	}
}
