package com.snowsense.smartalbum.sdk;

public class SmartAlbumSDKAsyncTest extends SmartAlbumSDKTestBase{
    @Override
    TestExecutor getTestExecutor() {
        return new AsyncTestExecutor();
    }
}
