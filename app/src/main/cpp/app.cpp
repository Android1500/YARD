
#include <jni.h>
#include "xposed-detector.h"

static int xposed_status = NO_XPOSED;

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_android1500_yard_CheckForRoot_getXposedStat(JNIEnv *env, jobject thiz) {
    int res = get_xposed_status(env, android_get_device_api_level());
    if (res > xposed_status) xposed_status = res;
    return xposed_status != NO_XPOSED;
}

// Library initializer
jint JNI_OnLoad(JavaVM *jvm, void *) {

    JNIEnv *env;

    if (jvm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    xposed_status = get_xposed_status(env, android_get_device_api_level());

    return JNI_VERSION_1_6;
}
