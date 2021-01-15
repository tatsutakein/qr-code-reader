@file:Suppress("PropertyName")

package com.takechee.qrcodereader.corecomponent

interface EnvVar {
    val DEBUG: Boolean // example: Boolean.parseBoolean("true");
    val APPLICATION_ID: String // example: "com.takechee.qrcodereader.debug";
    val BUILD_TYPE: String // example: "debug";
    val VERSION_CODE: Int // example: 1040000;
    val VERSION_NAME: String // example: "1.4.0-debug";

    // Field from default config.
    val GIT_COMMIT_HASH: String // example: "baba786";
}