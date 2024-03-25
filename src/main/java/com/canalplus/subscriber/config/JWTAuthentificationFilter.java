//package com.canalplus.subscriber.config;
//
//import com.canalplus.subscriber.services.JWTServiceImpl;
//import com.canalplus.subscriber.services.SubscriberService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse; ffdfdqfq
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JWTAuthentificationFilter extends OncePerRequestFilter {
//
//    private final JWTServiceImpl jwtService;
//
//    private SubscriberService SubscriberService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userMail;
//
//        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        userMail = jwtService.extractUserName(jwt);
//
//        if (StringUtils.isNotEmpty(userMail) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = SubscriberService.userDetailsService().loadUserByUsername(userMail);
//
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                securityContext.setAuthentication(token);
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
