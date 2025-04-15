/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client;

import id.dana.tech.model.client.constant.ConfigConstant;
import id.dana.tech.util.ConfigUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.dana.tech.model.client.enums.DANAEnvironment;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANAApiConfigTest.java, v 0.1 2025‐03‐26 20.28 SLW Exp $$
 */
public class DANAApiConfigTest {

    @BeforeEach
    public void setUp() {
        // Reset the singleton instance before each test
        DANAApiConfig.reset();
    }

    @Test
    public void testSingletonInstance() {
        DANAApiConfig config1 = DANAApiConfig.get();
        DANAApiConfig config2 = DANAApiConfig.get();
        assertSame(config1, config2, "Instances should be the same");
    }

    @Test
    public void testSingletonInstance2() {
        DANAApiConfig config1 = DANAApiConfig.builder().clientId("test-client-id")
            .merchantPrivateKey("test-private-key").danaPublicKey("test-public-key")
            .connectTimeout(1000).maxBodyLengthForLogging(4096)
            .environment(DANAEnvironment.PRODUCTION).build();

        DANAApiConfig config2 = DANAApiConfig.get();
        assertSame(config1, config2, "Instances should be the same");

        assertEquals("test-client-id", config2.getClientId());
        assertEquals("test-private-key", config2.getMerchantPrivateKey());
        assertEquals("test-public-key", config2.getDanaPublicKey());
        assertEquals(DANAEnvironment.PRODUCTION, config2.getEnvironment());
        assertEquals(1000, config2.getConnectTimeout());
        assertEquals(4096, config2.getMaxBodyLengthForLogging());
    }

    @Test
    public void testSingletonInstance3() {

        DANAApiConfig config1 = DANAApiConfig.get();
        DANAApiConfig config2 = DANAApiConfig.builder()
                .clientId("test-client-id")
                .merchantPrivateKey("test-private-key")
                .danaPublicKey("test-public-key")
                .connectTimeout(1000)
                .maxBodyLengthForLogging(4096)
                .environment(DANAEnvironment.PRODUCTION)
                .build();

        assertSame(config1, config2, "Instances should be the same");

        assertEquals(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_CLIENT_ID, ""),
            config2.getClientId());
        assertEquals(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_MERCHANT_PRIVATE_KEY, ""),
            config2.getMerchantPrivateKey());
        assertEquals(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_PUBLIC_KEY, ""),
            config2.getDanaPublicKey());
        assertEquals(DANAEnvironment.SANDBOX, config2.getEnvironment());
        assertEquals(3000, config2.getConnectTimeout());
        assertEquals(1024, config2.getMaxBodyLengthForLogging());
    }

    @Test
    public void testBuilderWithDefault() {
        DANAApiConfig config = DANAApiConfig.builder().clientId("test-client-id")
            .merchantPrivateKey("test-private-key").danaPublicKey("test-public-key")
            .environment(DANAEnvironment.PRODUCTION).build();

        assertEquals("test-client-id", config.getClientId());
        assertEquals("test-private-key", config.getMerchantPrivateKey());
        assertEquals("test-public-key", config.getDanaPublicKey());
        assertEquals(DANAEnvironment.PRODUCTION, config.getEnvironment());
        assertEquals(3000, config.getConnectTimeout());
        assertEquals(1024, config.getMaxBodyLengthForLogging());
    }

    @Test
    public void testBuilderAllCustom() {
        DANAApiConfig config = DANAApiConfig.builder().clientId("test-client-id")
            .merchantPrivateKey("test-private-key").danaPublicKey("test-public-key")
            .connectTimeout(1000).maxBodyLengthForLogging(4096)
            .environment(DANAEnvironment.PRODUCTION).build();

        assertEquals("test-client-id", config.getClientId());
        assertEquals("test-private-key", config.getMerchantPrivateKey());
        assertEquals("test-public-key", config.getDanaPublicKey());
        assertEquals(DANAEnvironment.PRODUCTION, config.getEnvironment());
        assertEquals(1000, config.getConnectTimeout());
        assertEquals(4096, config.getMaxBodyLengthForLogging());
    }

    @Test
    public void testBuilderValidation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DANAApiConfig.builder().clientId("").merchantPrivateKey("").danaPublicKey("").build();
        });

        String expectedMessage = "[DANAApiConfig] ClientID is blank!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDefaultValues() {
        DANAApiConfig config = DANAApiConfig.get();
        assertEquals(3000, config.getConnectTimeout());
        assertEquals(1024, config.getMaxBodyLengthForLogging());
    }
}