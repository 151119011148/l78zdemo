package com.scofen.util.sql;

import junit.framework.TestCase;

import java.io.IOException;

public class Model2SqlUtilsTest extends TestCase {

    public static final String projectId = "13kvcvr5-3gi-qw";
    public static final String rootModelId = "76278933501350976";

    public void testConvert() throws IOException {
        Model2SqlUtils.convert(projectId, rootModelId);
    }
}