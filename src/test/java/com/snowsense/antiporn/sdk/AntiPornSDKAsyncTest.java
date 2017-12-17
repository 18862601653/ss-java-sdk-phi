package com.snowsense.antiporn.sdk;

public class AntiPornSDKAsyncTest extends AntiPornSDKTestBase {
    @Override
    TestExecutor getTestExecutor() {
        return new AsyncTestExecutor();
    }


}
