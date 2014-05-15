// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Authenticating users.
 * 
 * @author cent4ur
 */
@WebFilter("*.xhtml")
public class AuthFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String reqUri = req.getRequestURI();
            if ((session != null && session.getAttribute("username") != null)
                    || reqUri.indexOf("/public/") >= 0
                    || reqUri.contains("javax.faces.resource")
                    || reqUri.indexOf("login.xhtml") >= 0) {
                logger.info("doFilter() - Authenticated user or public access.");
                chain.doFilter(request, response);
            } else {
                logger.info("doFilter() - Unknown user, redirecting.");
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            }
        } catch (Exception e) {
            logger.error("doFilter() - Exception: ", e);
        }
    }

    @Override
    public void destroy() {
    }
}
