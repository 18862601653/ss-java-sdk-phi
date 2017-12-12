package com.snowsense.face.sdk;

import org.junit.Ignore;

@Ignore
public class FaceSDKAsyncTest extends FaceSDKTestBase {
    @Override
    TestExecutor getTestExecutor() {
        return new AsyncTestExecutor();
    }
}
