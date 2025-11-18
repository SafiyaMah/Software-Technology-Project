package com.example.docpoll.security;

import com.example.docpoll.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.*;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<String> roles =
                jwt.getClaimAsStringList("roles") != null
                        ? jwt.getClaimAsStringList("roles")
                        : Collections.emptyList();

        var authorities = roles.stream()
                .map(r -> "ROLE_" + r.toUpperCase())
                .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                .toList();

        User user = new User(
                jwt.getClaim("preferred_username")
                //You get info, preffered username, email, first_name, last_name, idk
                //jwt.getClaim("email")
        );

        return new JwtAuthenticationToken(jwt, authorities, user.getUsername());
    }
}
