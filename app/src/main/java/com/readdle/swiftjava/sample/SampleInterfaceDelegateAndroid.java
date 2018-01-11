package com.readdle.swiftjava.sample;

import com.readdle.codegen.anotation.SwiftCallbackFunc;
import com.readdle.codegen.anotation.SwiftDelegate;

@SwiftDelegate(importPackages = {"SampleAppCore"}, protocols = {"SampleBlockDelegate"})
public interface SampleInterfaceDelegateAndroid {

    @SwiftCallbackFunc
    void onCall();

}
