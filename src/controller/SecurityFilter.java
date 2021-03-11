package by.epam.jvd.vitebsk.task4.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.jvd.vitebsk.task4.domain.Role;
import by.epam.jvd.vitebsk.task4.domain.User;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>(); // permissions - разрешение

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> entrant = new HashSet<>();
        entrant.add(Role.ENTRANT);

        permissions.put("/logout", all);
        permissions.put("/password/edit", all);
        permissions.put("/password/save", all);
        permissions.put("/statement/delete", all);
        permissions.put("/statement/save", all);
        permissions.put("/statement/edit", all);
        permissions.put("/user/edit", all);
        permissions.put("/entrant/list", all);
        permissions.put("/user/edit", all);
        permissions.put("/user/save", all);
        permissions.put("/statement/list", admin);
        permissions.put("/password/reset", admin);
        permissions.put("/user/list", admin);
        permissions.put("/user/delete", admin);
        permissions.put("/faculty/list", admin);
        permissions.put("/faculty/edit", admin);
        permissions.put("/faculty/save", admin);
        permissions.put("/faculty/delete", admin);
        permissions.put("/enrolledentrants/list", admin);
        permissions.put("/enrolledentrants/create", admin);
        permissions.put("/enrolledentrants/reset", admin);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<Role> roles = permissions.get(url);
        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("currentUser");
                if (user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() {
    }
}
