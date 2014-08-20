#include "src/main/java/com_goeswhere_jmh_Exp.h"

#include "exp.c"

JNIEXPORT jdouble JNICALL Java_com_goeswhere_jmh_Exp_calculate
  (JNIEnv *env, jclass, jdoubleArray values) {
    jdouble *jdoubles = env->GetDoubleArrayElements(values, NULL);
    double result = go(jdoubles, env->GetArrayLength(values));
    env->ReleaseDoubleArrayElements(values, jdoubles, JNI_ABORT);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_goeswhere_jmh_Exp_calculatePointer
  (JNIEnv *env, jclass, jlong ptr, jint length) {
    return go((double*)ptr, length);
}

