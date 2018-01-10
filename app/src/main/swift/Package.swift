// swift-tools-version:4.0

import PackageDescription

let package = Package(
    name: "SampleAppCore",
    products:[
        .library(
            name: "SampleAppCoreBridge", 
            type: .dynamic, 
            targets:["SampleAppCoreBridge"]
        )
    ],
    dependencies: [
        .package(url: "https://github.com/andriydruk/swift-java-coder.git", .branch("master")),
        .package(url: "https://github.com/andriydruk/java_swift.git", .branch("master")),
        .package(url: "https://github.com/andriydruk/swift-java.git", .branch("master")),
    ],
    targets: [
        .target(
            name: "SampleAppCore",
            path: "Sources"
        ),
        .target(
            name: "SampleAppCoreBridge",
            dependencies: ["SampleAppCore", "Java", "JavaCoder", "java_swift"],
            path: ".build/generated"
        ),
    ],
    swiftLanguageVersions: [4]
)
