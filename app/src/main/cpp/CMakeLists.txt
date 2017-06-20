cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -UNDEBUG")

add_library(ZcwfengJniLibName SHARED
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/api_server.c
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/api_server.db
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/db_plugin_sqlite.c
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/jnitest.c
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/mongoose.c
            /Users/zcw/workspace/android_fast_dev_tool/app/src/main/jni/sqlite3.c)

# Include libraries needed for native-media-jni lib
target_link_libraries(ZcwfengJniLibName
                      android
                      log
                      OpenMAXAL)

