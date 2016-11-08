LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := ZcwfengJniLibName
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_LDLIBS := \
	-llog \
	-lz \
	-lm \

LOCAL_SRC_FILES := \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/api_server.c \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/api_server.db \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/db_plugin_sqlite.c \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/jnitest.c \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/mongoose.c \
	/Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni/sqlite3.c \

LOCAL_C_INCLUDES += /Users/zcw/workspace/AndroidFastDevTool/app/src/main/jni
LOCAL_C_INCLUDES += /Users/zcw/workspace/AndroidFastDevTool/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)

APP_PLATFORM=9