package com.fsse2401.backend_project_redo02.data.user.domainObject;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class FirebaseUserData {
    private String firebaseUid;
    private String email;

    public FirebaseUserData(JwtAuthenticationToken token) {
        this.firebaseUid = (String) token.getTokenAttributes().get("user_id");
        this.email = (String) token.getTokenAttributes().get("email");
    }

    public FirebaseUserData() {
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
