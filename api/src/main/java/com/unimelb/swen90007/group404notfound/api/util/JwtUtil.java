package com.unimelb.swen90007.group404notfound.api.util;

import com.unimelb.swen90007.group404notfound.api.domain.Admin;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyUser;
import com.unimelb.swen90007.group404notfound.api.domain.Customer;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    // generate key
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Generate jwt token after user logins successfully
     * @param ttlMillis jwt expired time
     * @param user      user object for login successfully
     * @return String of generated token
     */
    public static String createJwt(long ttlMillis, User user) {

        // jwt generation time
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);


        // payload
        Map<String, Object> claims = new HashMap<>();
        String uniqueId = null;
        if (user instanceof Customer){
            uniqueId = "Customer" + "_" + user.getUserId();

        }
        if (user instanceof CompanyUser){
            uniqueId = "Company" + "_" + user.getUserId();
        }
        if (user instanceof Admin){
            uniqueId = "Admin" + "_" + user.getUserId();
        }
        claims.put("uniqueId", uniqueId);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt issued time
                .setIssuedAt(date)
                // jwt main body
                .setSubject(uniqueId)
                .signWith(key);

        // set jwt expired time
        if (ttlMillis >= 0) {
            long expMillis = ttlMillis + nowMillis;
            Date expDate = new Date(expMillis);
            jwtBuilder.setExpiration(expDate);
        }
        System.out.println("Generate jwt");
        return jwtBuilder.compact();
    }

    /**
     * decrypt jwt
     * @param token token needs to be decrypted
     * @param user user object
     * @return decrypted claims
     */
    public static Claims parseJWT(String token,User user){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            // Token expired
            System.out.println("Token expired");
            return null;
        } catch (SignatureException e) {
            // Invalid signature
            System.out.println("Invalid token signature");
            return null;
        }
    }


    /**
     * decide whether the username is the same as the one in the token
     * @param token token needs to be decrypted
     * @param user user object
     * @return boolean that username is the same or not
     */
    public static Boolean isVerify(String token, User user) {
        Claims claims = parseJWT(token, user);

        System.out.println("claims-----》" + claims);

        // Check if claims is null or doesn't contain "uniqueId"
        if (claims == null || !claims.containsKey("uniqueId")) {
            return false;
        }

        // Compare the unique id
        String uniqueId = null;
        if (user instanceof Customer){
            uniqueId = "Customer" + "_" + user.getUserId();

        }
        if (user instanceof CompanyUser){
            uniqueId = "Company" + "_" + user.getUserId();
        }
        if (user instanceof Admin){
            uniqueId = "Admin" + "_" + user.getUserId();
        }
        String userInToken = claims.get("uniqueId", String.class);
        return uniqueId.equals(userInToken);
    }
}
