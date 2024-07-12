package com.scofen.util.sql;

import junit.framework.TestCase;

import java.io.IOException;

public class Model2SqlUtilsTest extends TestCase {

    public static final String projectId = "129qu38t-6w8-622";
    public static final String rootModelId = "78180549862329920";

    public void testConvert() throws IOException {
        Model2SqlUtils.convert(projectId, rootModelId);
    }
}