# README

## Project Overview
This project is an Android application built with Kotlin and leverages several modern libraries and frameworks, including Jetpack Compose, Dagger Hilt for dependency injection, and Room for database management. The application is configured for a minimum SDK of 26 and targets SDK 34.

## Configuration Options

### Android Build Configuration

The `android` block in the build script is configured as follows:

- **namespace**: `com.abztest`
- **compileSdk**: 34
- **defaultConfig**:
  - `applicationId`: `com.abztest`
  - `minSdk`: 26
  - `targetSdk`: 34
  - `versionCode`: 1
  - `versionName`: "1.0"
  - `testInstrumentationRunner`: `"androidx.test.runner.AndroidJUnitRunner"`
  - `vectorDrawables.useSupportLibrary`: `true`
- **buildTypes**:
  - **release**:
    - `isMinifyEnabled`: `true`
    - `proguardFiles`: Includes `proguard-android-optimize.txt` and `proguard-rules.pro`
- **compileOptions**:
  - `sourceCompatibility`: `JavaVersion.VERSION_1_8`
  - `targetCompatibility`: `JavaVersion.VERSION_1_8`
- **kotlinOptions**:
  - `jvmTarget`: `"1.8"`
- **buildFeatures**:
  - `compose`: `true`
- **composeOptions**:
  - `kotlinCompilerExtensionVersion`: `"1.5.1"`
- **packaging**:
  - `resources.excludes`: `"/META-INF/{AL2.0,LGPL2.1}"`

### Dependencies

The project relies on several dependencies, including:

- **Hilt for Dependency Injection**:
  - `implementation "androidx.hilt:hilt-navigation-compose:1.0.0"`
  - `implementation "com.google.dagger:hilt-android:2.51.1"`
  - `kapt "com.google.dagger:hilt-compiler:2.51.1"`
  - `androidTestImplementation "com.google.dagger:hilt-android-testing:2.51.1"`
  - `kaptAndroidTest "com.google.dagger:hilt-compiler:2.51.1"`
  - `testImplementation "com.google.dagger:hilt-android-testing:2.51.1"`
  - `kaptTest "com.google.dagger:hilt-compiler:2.51.1"`

- **Room for Database Management**:
  - `kapt "androidx.room:room-compiler:2.6.1"`
  - `implementation "androidx.room:room-runtime:2.6.1"`
  - `implementation "androidx.room:room-ktx:2.6.1"`

- **Jetpack Libraries**:
  - `implementation(libs.androidx.core.ktx)`
  - `implementation(libs.androidx.lifecycle.runtime.ktx)`
  - `implementation(libs.androidx.activity.compose)`
  - `implementation(platform(libs.androidx.compose.bom))`
  - `implementation(libs.androidx.ui)`
  - `implementation(libs.androidx.ui.graphics)`
  - `implementation(libs.androidx.ui.tooling.preview)`
  - `implementation(libs.androidx.material3)`
  - `testImplementation(libs.junit)`
  - `androidTestImplementation(libs.androidx.junit)`
  - `androidTestImplementation(libs.androidx.espresso.core)`
  - `androidTestImplementation(platform(libs.androidx.compose.bom))`
  - `androidTestImplementation(libs.androidx.ui.test.junit4)`
  - `debugImplementation(libs.androidx.ui.tooling)`
  - `debugImplementation(libs.androidx.ui.test.manifest)`

## Customization

To customize the build configuration and dependencies, modify the respective blocks in the `build.gradle` file. For example:

- To change the `minSdk` version, update the `minSdk` value in the `defaultConfig` block.
- To add a new dependency, include it in the `dependencies` block.

## Dependencies

This project uses the following external libraries and plugins:

- **Android Gradle Plugin**: For building the Android application.
- **Kotlin Gradle Plugin**: For Kotlin support.
- **Dagger Hilt**: For dependency injection.
- **Room**: For database management.
- **Jetpack Compose**: For UI development.
- **JUnit**: For testing.

These dependencies are declared in the `build.gradle` file and managed through Gradle.

## Troubleshooting Tips

### Common Issues

1. **Dependency Resolution Failures**:
   - Ensure all dependencies are correctly declared in the `dependencies` block.
   - Sync the project with Gradle files after making changes.

2. **Build Failures**:
   - Check the `compileSdk`, `minSdk`, and `targetSdk` versions for compatibility.
   - Ensure that the correct `proguardFiles` are specified in the `release` build type.

3. **Hilt Configuration Errors**:
   - Ensure Hilt dependencies are included correctly.
   - Annotate application class with `@HiltAndroidApp`.

4. **Room Database Issues**:
   - Ensure Room dependencies are included.
   - Annotate data classes and DAOs properly for Room.

5. **Jetpack Compose Issues**:
   - Ensure `compose` is enabled in `buildFeatures`.
   - Use the correct `kotlinCompilerExtensionVersion`.

### Solutions

- **Sync Issues**: Re-sync the project with Gradle files (`File > Sync Project with Gradle Files`).
- **Clean Build**: Perform a clean build (`Build > Clean Project`) and then rebuild the project (`Build > Rebuild Project`).
- **Invalidate Caches**: Invalidate and restart Android Studio (`File > Invalidate Caches / Restart`).

For more detailed troubleshooting, refer to the official documentation of each library and tool used in the project.
