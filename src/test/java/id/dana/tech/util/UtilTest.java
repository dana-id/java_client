/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: UtilTest.java, v 0.1 2025‐04‐07 12.33 SLW Exp $$
 */
public class UtilTest {

    @Test
    public void printEnvVars() {
        System.out.println("DANA_PUBLIC_KEY = " + System.getenv("DANA_PUBLIC_KEY"));
    }

    @Test
    public void configUtil() {
        assertEquals(30, ConfigUtil.getConfigInt("DANA_PUBLIC_KEY", 30));
    }

    @Test
    public void hashUtil1() {
        String input = "test";
        try {
            String hexEncoded = HashUtil.sha256HexEncode(input);
            String base64Encoded = HashUtil.sha256Base64Encode(input);
            assertTrue(Arrays.equals(Hex.decodeHex(hexEncoded), Base64.getDecoder().decode(base64Encoded)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void hashUtil2() {
        String input = "test";
        try {
            String hexEncoded = HashUtil.hmacSha512HexEncode(input, "key");
            String base64Encoded = HashUtil.hmacSha512Base64Encode(input, "key");
            assertTrue(Arrays.equals(Hex.decodeHex(hexEncoded), Base64.getDecoder().decode(base64Encoded)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}