/**
 * DANA.id
 * Copyright (c) 2017‐2025 All Rights Reserved.
 */
package id.dana.tech.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import id.dana.tech.model.client.enums.ApiType;
import id.dana.tech.model.client.enums.DANAEnvironment;
import id.dana.tech.model.client.enums.SNAPHeader;

/**
 * @author SLW (sumitro.wijaya@dana.id)
 * @version $Id: EnumTest.java, v 0.1 2025‐04‐09 15.55 SLW Exp $$
 */
public class EnumTest {
    
    @Test
    public void danaEnvironmentEnumTest() {

        for (DANAEnvironment e : DANAEnvironment.values()) {
            DANAEnvironment e2 = DANAEnvironment.getByName(e.name());
            assertEquals(e, e2);
            assertEquals(e.getUrl(), e2.getUrl());
        }
        assertNull(DANAEnvironment.getByName("INVALID_ENV"));
    }

    @Test
    public void danaSnapHeaderTest() {

        for (SNAPHeader e : SNAPHeader.values()) {
            SNAPHeader e2 = SNAPHeader.getByCode(e.getCode());
            assertEquals(e, e2);
            assertEquals(e.getCode(), e2.getCode());
            assertEquals(e.getHeaderName(), e2.getHeaderName());
        }
        assertNull(SNAPHeader.getByCode("CODE"));
    }

    @Test
    public void danaApiTypeTest() {

        for (ApiType e : ApiType.values()) {
            ApiType e2 = ApiType.getByName(e.name());
            assertEquals(e, e2);
        }
        assertNull(ApiType.getByName("CODE"));
    }
}