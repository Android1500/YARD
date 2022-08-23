cmake_minimum_required(VERSION 3.22.1)

add_library(
        app
        SHARED
        app.cpp
)

find_library(
        app
        log
)

target_link_libraries(
        app
        ${log_lib}
)
find_package(xposeddetector REQUIRED CONFIG)
target_link_libraries(app xposeddetector::xposed_detector)
