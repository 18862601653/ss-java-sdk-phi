package com.snowsense.face.sdk;

public class GrpFaceSyncTest extends GrpFaceTestBase {
    @Override
    TestExecutor getTestExecutor() {
        return new SyncTestExecutor();
    }
}
