/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: RequestBodyUtil.java, v 0.1 2025‐04‐07 04.38 SLW Exp $$
 */
public class RequestUtil {

    public static String peekRequestBody(Request originalRequest, int maxBodyLengthForLogging) {

        RequestBody originalBody = originalRequest.body();

        try {
            if (originalBody == null || originalBody.contentLength() == 0) {
                return "{}";
            } else {
                TempRequestBody tempRequestBody = new TempRequestBody(originalBody);
                String peekedBody = tempRequestBody.peekBody();
                return peekedBody.substring(0,
                    Math.min(peekedBody.length(), maxBodyLengthForLogging));
            }
        } catch (IOException e) {
            return "{}";
        }
    }

    public static String peekRequestBody(Request originalRequest) {

        RequestBody originalBody = originalRequest.body();

        try {
            if (originalBody == null || originalBody.contentLength() == 0) {
                return "{}";
            } else {
                TempRequestBody tempRequestBody = new TempRequestBody(originalBody);
                return tempRequestBody.peekBody();
            }
        } catch (IOException e) {
            return "{}";
        }
    }

    static class TempRequestBody extends RequestBody {

        private final RequestBody           originalBody;
        private final ByteArrayOutputStream copyStream = new ByteArrayOutputStream();

        TempRequestBody(RequestBody originalBody) {
            this.originalBody = originalBody;
        }

        @Override
        public MediaType contentType() {
            return originalBody.contentType();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            Buffer buffer = new Buffer();
            originalBody.writeTo(buffer);
            buffer.copyTo(copyStream);
            buffer.writeTo(sink.outputStream());
        }

        public String peekBody() {
            Buffer dummySink = new Buffer();
            try {
                writeTo(dummySink);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new String(copyStream.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    public static String generateExternalId() {
        return UUID.randomUUID().toString();
    }
}