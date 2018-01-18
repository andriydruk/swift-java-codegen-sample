package com.readdle.swiftjava.sample;

import com.readdle.codegen.anotation.SwiftCallbackFunc;
import com.readdle.codegen.anotation.SwiftDelegate;
import com.readdle.codegen.anotation.SwiftError;
import com.readdle.codegen.anotation.SwiftReference;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@SwiftReference(importPackages = {"SampleAppCore"})
public class SampleReference {

    @SwiftReference(importPackages = {"SampleAppCore"})
    public static class SampleReferenceEnclose {


        // Swift JNI private native pointer
        private long nativePointer = 0L;

        // Swift JNI private constructor
        // Should be private. Don't call this constructor from Java!
        private SampleReferenceEnclose() {
        }

        // Swift JNI release method
        public native void release();

        @NonNull
        public static native SampleReferenceEnclose init();
    }

    @SwiftDelegate(importPackages = {"SampleAppCore"}, protocols = {"SampleBlockDelegate"})
    public interface SampleInterfaceDelegateAndroid {

        @SwiftCallbackFunc
        void onCall();

    }

    // Swift JNI private native pointer
    private long nativePointer = 0L;

    // Swift JNI private constructor
    // Should be private. Don't call this constructor from Java!
    private SampleReference() {
    }

    // Swift JNI release method
    public native void release();

    @NonNull
    public native SampleValue getRandomValue();

    public native void saveValue(@NonNull SampleValue value);

    public native void funcThrows() throws SwiftError;

    @Nullable
    public native SampleReference funcWithNil();

    @NonNull
    public static native SampleReference init();

    // TODO: Impossible to generate for now. Add extra check for JavaSwift protocol before casting to .javaObject()
    //@Nullable @SwiftFunc
    //public native SampleDelegateAndroid getDelegate();

    public native void setDelegate(SampleDelegateAndroid delegate);

    @NonNull
    public native Long tick();

    public native void tickWithBlock(@NonNull SampleInterfaceDelegateAndroid delegate);

    @NonNull
    public native Float floatCheck(@NonNull Float var1);

    @NonNull
    public native Double doubleCheck(@NonNull Double var1);

    @NonNull
    public native Exception exceptionCheck(@NonNull Exception var1);

    @NonNull
    public native SampleReferenceEnclose enclose(@NonNull SampleReferenceEnclose var1);

}
