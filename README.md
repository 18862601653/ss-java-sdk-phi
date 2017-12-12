# Java SDK

SnowSense Java SDK for face recognition web service.

You may need [IntelliJ IDEA](https://www.jetbrains.com/idea/download) to open and build the project.

## Download

Download the [latest JAR](https://github.com/SnowSense/snowsense-java-sdk/releases/download/v1.0/face-java-sdk-v1.0.jar) or copy the source code under https://github.com/SnowSense/snowsense-java-sdk/tree/master/src/main to your project.

## Dependecies
This SDK dependes on some 3rd party libraries, add them in your project via Maven
```
<dependency>
  <groupId>com.squareup.retrofit2</groupId>
  <artifactId>retrofit</artifactId>
  <version>2.3.0</version>
</dependency>

<dependency>
  <groupId>com.squareup.retrofit2</groupId>
  <artifactId>converter-gson</artifactId>
  <version>2.3.0</version>
</dependency>

```

or Gradle
```
compile 'com.squareup.retrofit2:retrofit:2.3.0'
compile 'com.squareup.retrofit2:converter-gson:2.3.0'

```

## Usage
See usage and examples in the [API usage](https://github.com/SnowSense/snowsense-java-sdk/blob/master/API_Docs.md) and [test cases](https://github.com/SnowSense/snowsense-java-sdk/blob/master/src/test/java/com/snowsense/face/sdk/FaceSDKTest.java) 

## License
```
Copyright 2017 SnowSense, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
