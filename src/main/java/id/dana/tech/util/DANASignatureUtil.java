/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.apache.hc.client5.http.utils.Hex;

import id.dana.tech.model.client.DANAApiConfig;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: DANASignatureUtil.java, v 0.1 2025‐03‐28 02.38 SLW Exp $$
 */
public class DANASignatureUtil {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private static final String  RSA_SHA256      = "SHA256withRSA";

    public static byte[] sign(String stringToSign, String keyAlgo,
                              String signAlgo) throws NoSuchAlgorithmException,
                                               InvalidKeySpecException, InvalidKeyException,
                                               SignatureException {

        byte[] keyBytes = Base64.getDecoder().decode(DANAApiConfig.get().getMerchantPrivateKey());

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(keyAlgo);
        PrivateKey privateKey = kf.generatePrivate(spec);

        Signature signature = Signature.getInstance(signAlgo);
        signature.initSign(privateKey);
        signature.update(stringToSign.getBytes(DEFAULT_CHARSET));

        return signature.sign();
    }

    public static String signSHA256RSA(String stringToSign) throws Exception {
        return Base64.getEncoder().encodeToString(sign(stringToSign, "RSA", RSA_SHA256));
    }

    public static String signSHA256RSAHex(String stringToSign) throws Exception {
        return Hex.encodeHexString(sign(stringToSign, "RSA", RSA_SHA256));
    }

    public static boolean verifySHA256RSA(String signature, String signedString) throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(DANAApiConfig.get().getDanaPublicKey());

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(spec);

        Signature sig = Signature.getInstance(RSA_SHA256);
        sig.initVerify(publicKey);
        sig.update(signedString.getBytes(DEFAULT_CHARSET));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return sig.verify(signatureBytes);
    }

    public static class SNAP {

        /**
         * generate asymmetric signature
         * @param payload
         * @param httpMethod
         * @param url
         * @param timestamp
         * @return
         */
        public static String generateAsymmetricSignature(String payload, String httpMethod,
                                                         String url, String timestamp) {

            payload = StringUtil.defaultIfBlank(payload);
            LogUtil.debug("[DANASignatureUtil] rawPayload: " + payload);
            try {
                return signSHA256RSA(asymmetricStrToSign(payload, url, httpMethod, timestamp));
            } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                    | InvalidKeySpecException e) {

                LogUtil
                    .error("Failed to generate asymmetric signature for SNAP: " + e.getMessage());
                return null;
            } catch (Exception e) {
                LogUtil.error(
                    "Unknown error to generate asymmetric signature for SNAP: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        /**
         * verifyAsymmetricSignature
         * @param signature
         * @param payload
         * @param httpMethod
         * @param url
         * @param timestamp
         */
        public static void verifyAsymmetricSignature(String signature, String payload,
                                                     String httpMethod, String url,
                                                     String timestamp) {

            try {
                boolean isVerified = verifySHA256RSA(signature,
                    asymmetricStrToSign(payload, url, httpMethod, timestamp));
                if (!isVerified) {

                }
            } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                    | InvalidKeySpecException e) {

                LogUtil.error("Failed to verify asymmetric signature for SNAP: " + e.getMessage());
            } catch (Exception e) {
                LogUtil.error(
                    "Unknown error to verify asymmetric signature for SNAP:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        /**
         * compose SNAP asymmetric signature's string to sign
         * <HTTP METHOD> + ”:” + <RELATIVE PATH URL> + “:“ + LowerCase(HexEncode(SHA-256(Minify(<HTTP BODY>)))) + “:“ + <X-TIMESTAMP>
         * @param payload
         * @param url
         * @param httpMethod
         * @param timestamp
         * @return
         * @throws NoSuchAlgorithmException
         */
        private static String asymmetricStrToSign(String payload, String url, String httpMethod,
                                                  String timestamp) throws NoSuchAlgorithmException {

            String strToSign = httpMethod + ":" + url + ":"
                               + HashUtil.sha256HexEncode(payload).toLowerCase() + ":" + timestamp;

            LogUtil.debug("[DANASignatureUtil] strToSign: " + strToSign);
            return strToSign;
        }

        /**
         * compose SNAP symmetric signature's string to sign
         * <HTTP METHOD> + ”:” + <RELATIVE PATH URL> + ”:” + <ACCESS TOKEN> + “:“ + LowerCase(HexEncode(SHA-256(Minify(<HTTP BODY>)))) + “:“ + <X-TIMESTAMP>
         * @param payload
         * @param url
         * @param httpMethod
         * @param timestamp
         * @return
         * @throws NoSuchAlgorithmException
         */
        private static String symmetricStrToSign(String payload, String url, String httpMethod,
                                                 String timestamp,
                                                 String accessToken) throws NoSuchAlgorithmException {

            String strToSign = httpMethod + ":" + url + ":" + accessToken + ":"
                               + HashUtil.sha256HexEncode(payload).toLowerCase() + ":" + timestamp;

            LogUtil.debug("[DANASignatureUtil] strToSign: " + strToSign);
            return strToSign;
        }

        /**
         * generate applyToken signature
         * @param timestamp
         * @return
         */
        public static String generateApplyTokenSignature(String timestamp) {

            try {
                return signSHA256RSA(asymmetricStrToSign(timestamp));
            } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                    | InvalidKeySpecException e) {

                LogUtil.error("Failed to generate applyToken signature for SNAP:" + e.getMessage());
                return null;
            } catch (Exception e) {
                LogUtil.error(
                    "Unknown error to generate applyToken signature for SNAP:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        public static String generateApplyTokenSignatureHex(String timestamp) {

            try {
                return signSHA256RSAHex(asymmetricStrToSign(timestamp));
            } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                    | InvalidKeySpecException e) {

                LogUtil.error("Failed to generate applyToken signature for SNAP:" + e.getMessage());
                return null;
            } catch (Exception e) {
                LogUtil.error(
                    "Unknown error to generate applyToken signature for SNAP:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        /**
         * verifyApplyTokenSignature
         * @param signature
         * @param timestamp
         */
        public static void verifyApplyTokenSignature(String signature, String timestamp) {

            try {
                boolean isVerified = verifySHA256RSA(signature, asymmetricStrToSign(timestamp));

                if (!isVerified) {

                }
            } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException
                    | InvalidKeySpecException e) {

                LogUtil.error("Failed to verify applyToken signature for SNAP:" + e.getMessage());
            } catch (Exception e) {
                LogUtil.error(
                    "Unknown error to verify applyToken signature for SNAP:" + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        /**
         * compose SNAP asymmetric signature's string to sign FOR apply token
         * <X-CLIENT-KEY> + “|“ + <X-TIMESTAMP>
         * @param timestamp
         * @return
         * @throws NoSuchAlgorithmException
         */
        private static String asymmetricStrToSign(String timestamp) throws NoSuchAlgorithmException {

            return DANAApiConfig.get().getClientId() + "|" + timestamp;
        }
    }
}