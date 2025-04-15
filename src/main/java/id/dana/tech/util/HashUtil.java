/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: HashUtil.java, v 0.1 2025‐04‐06 04.03 SLW Exp $$
 */
public class HashUtil {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private static final String  SHA256          = "SHA-256";

    private static final String  HMAC_SHA512     = "HmacSHA512";

    public static String sha256HexEncode(String data) throws NoSuchAlgorithmException {
        return encodeHex(sha256Digest(data));
    }

    public static String sha256Base64Encode(String data) throws NoSuchAlgorithmException {
        return encodeBase64(sha256Digest(data));
    }

    public static String hmacSha512HexEncode(String data,
                                             String key) throws NoSuchAlgorithmException,
                                                         InvalidKeyException {
        return encodeHex(hmacSha512Digest(data, key));
    }

    public static String hmacSha512Base64Encode(String data,
                                                String key) throws NoSuchAlgorithmException,
                                                            InvalidKeyException {
        return encodeBase64(hmacSha512Digest(data, key));
    }

    private static byte[] sha256Digest(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA256);
        return md.digest(data.getBytes());
    }

    private static byte[] hmacSha512Digest(String data, String key) throws NoSuchAlgorithmException,
                                                                    InvalidKeyException {
        Mac mac = Mac.getInstance(HMAC_SHA512);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(DEFAULT_CHARSET), HMAC_SHA512);
        mac.init(secretKey);
        return mac.doFinal(data.getBytes(DEFAULT_CHARSET));
    }

    private static String encodeHex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    private static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}