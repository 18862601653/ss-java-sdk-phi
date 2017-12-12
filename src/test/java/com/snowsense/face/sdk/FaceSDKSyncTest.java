package com.snowsense.face.sdk;

public class FaceSDKSyncTest extends FaceSDKTestBase {
    @Override
    TestExecutor getTestExecutor() {
        return new SyncTestExecutor();
    }
}
