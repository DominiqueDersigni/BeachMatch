package nl.novi.beachmatch.helpers;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component("securityHelper")
public class SecurityHelper {
    public static boolean isOwner(Jwt jwt, String username){
        return username.equals(jwt.getClaimAsString("preferred_username"));
    }
}
