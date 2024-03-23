package com.fsse2401.backend_project_redo02.util;

import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData(JwtAuthenticationToken token){
        return new FirebaseUserData(token);
    }
}
