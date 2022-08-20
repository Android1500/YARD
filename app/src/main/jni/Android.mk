LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE           := app
LOCAL_CFLAGS           := -Os
LOCAL_SRC_FILES        := app.cpp
LOCAL_STATIC_LIBRARIES := xposed_detector
include $(BUILD_SHARED_LIBRARY)

#include ../xposeddetector/src/main/jni/Android.mk
$(call import-module,prefab/xposeddetector)