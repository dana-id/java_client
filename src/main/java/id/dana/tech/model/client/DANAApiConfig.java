/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model.client;

import org.apache.commons.lang3.Validate;

import id.dana.tech.model.client.constant.ConfigConstant;
import id.dana.tech.model.client.enums.DANAEnvironment;
import id.dana.tech.util.ConfigUtil;
import id.dana.tech.util.LogUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANADANAApiConfig.java, v 0.1 2025‐03‐20 20.26 SLW Exp $$
 */
@Getter
@Setter
@Builder
public class DANAApiConfig {

    @Builder.Default
    private DANAEnvironment               environment             = DANAEnvironment.SANDBOX;

    @Builder.Default
    private String                        clientId                = ConfigUtil
        .getConfig(ConfigConstant.ENV_KEY_DANA_CLIENT_ID, "");

    @Builder.Default
    private String                        merchantPrivateKey      = ConfigUtil
        .getConfig(ConfigConstant.ENV_KEY_DANA_MERCHANT_PRIVATE_KEY, "");

    @Builder.Default
    private String                        danaPublicKey           = ConfigUtil
        .getConfig(ConfigConstant.ENV_KEY_DANA_PUBLIC_KEY, "");

    @Builder.Default
    private String                        channelId               = ConfigUtil
        .getConfig(ConfigConstant.ENV_KEY_DANA_CHANNEL_ID, "");

    @Builder.Default
    private int                           connectTimeout          = 3000;

    @Builder.Default
    private int                           maxBodyLengthForLogging = 1024;

    private static volatile DANAApiConfig instance;

    private static boolean                isLazyLoaded            = false;

    private DANAApiConfig(DANAEnvironment environment, String clientId, String merchantPrivateKey,
                          String danaPublicKey, String channelId, int connectTimeout,
                          int maxBodyLengthForLogging) {

        this.environment = environment;
        this.clientId = clientId;
        this.merchantPrivateKey = merchantPrivateKey;
        this.danaPublicKey = danaPublicKey;
        this.channelId = channelId;
        this.connectTimeout = connectTimeout;
        this.maxBodyLengthForLogging = maxBodyLengthForLogging;
    }

    public static DANAApiConfig get() {
        if (instance == null) {
            synchronized (DANAApiConfig.class) {
                if (instance == null) {
                    LogUtil.warn("[DANAApiConfig] Using DEFAULT configuration!");
                    isLazyLoaded = true;
                    instance = DANAApiConfig.builder().environment(DANAEnvironment.SANDBOX)
                        .clientId(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_CLIENT_ID, ""))
                        .merchantPrivateKey(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_MERCHANT_PRIVATE_KEY, ""))
                        .danaPublicKey(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_PUBLIC_KEY, ""))
                        .channelId(ConfigUtil.getConfig(ConfigConstant.ENV_KEY_DANA_CHANNEL_ID, ""))
                        .connectTimeout(ConfigUtil.getConfigInt(ConfigConstant.ENV_KEY_DANA_CONNECT_TIMEOUT, 3000))
                        .maxBodyLengthForLogging(ConfigUtil.getConfigInt(ConfigConstant.ENV_KEY_DANA_LOGGING_MAX_BODY, 1024))
                        .build();
                }
            }
        }
        return instance;
    }

    public static DANAApiConfigBuilder builder() {

        return new DANAApiConfigSingletonBuilder();
    }

    private static class DANAApiConfigSingletonBuilder extends DANAApiConfigBuilder {

        @Override
        public DANAApiConfig build() {

            if (instance == null) {
                synchronized (DANAApiConfig.class) {
                    if (instance == null) {
                        if (!isLazyLoaded) {
                            LogUtil.warn("[DANAApiConfig] Using User-defined configuration!");
                        }
                        DANAApiConfig temp = super.build();

                        Validate.notBlank(temp.getClientId(), "[DANAApiConfig] ClientID is blank!");
                        Validate.notBlank(temp.getMerchantPrivateKey(),
                            "[DANAApiConfig] MerchantPrivateKey is blank!");
                        Validate.notBlank(temp.getDanaPublicKey(),
                            "[DANAApiConfig] DANAPublicKey is blank!");
                        Validate.notBlank(temp.getChannelId(),
                            "[DANAApiConfig] ChannelID is blank!");

                        instance = temp;
                    }
                }
            }

            return instance;
        }
    }

    public static void reset() {
        if (instance != null) {
            synchronized (DANAApiConfig.class) {
                if (instance != null) {
                    LogUtil.warn("[DANAApiConfig] Reset!");
                    instance = null;
                }
            }
        }
    }
}
