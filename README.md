# VL Media Interview Assignment

This repository contains the codebase for the VL Media Android Challenge.

## Libraries

The libraries used in this codebase are listed below. Please note that the core Android libraries are not listed, only the optional ones are listed.

| Name                                                                                                       | Usage                | Details                                                                                     |
|------------------------------------------------------------------------------------------------------------|----------------------|---------------------------------------------------------------------------------------------|
| [Jetpack Compose](https://developer.android.com/jetpack/compose)                                           | UI Toolkit           | UI Components and screens are implemented.                                                  |
| [Material3](https://developer.android.com/jetpack/compose/designsystems/material3)                         | Theming              | Theme, Typography and Color Scheme created with Material3.                                  |
| [Hilt(Dagger)](https://developer.android.com/training/dependency-injection/hilt-android)                   | Dependency Injection | Hilt was used to handle DI.                                                                 |
| [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)                             | Navigation           | Handles navigating between different screens (composables), with type safe arguments.       |
| [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)         | Utility              | Helps injecting ViewModels into composables in navigation graph.                            |
| [Retrofit](https://github.com/square/retrofit)                                                             | Network              | A type-safe HTTP client for Android.                                                        |
| [Retrofit Gson Converter](https://square.github.io/retrofit/)                                              | Utility              | Helps converting responses into Kotlin classes.                                             |
| [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)      | Utility              | Helps development by logging by intercepting network calls.                                 |
| [Landscapist Glide](https://github.com/skydoves/landscapist)                                               | ImageLoader          | Jetpack Compose image loading solution that fetches and displays network images with Glide. |
| [Mockk](https://mockk.io)                                                                                  | Test                 | Creates mock classes for the required dependencies. Makes testing easier.                   |
| [Kotlin Coroutines Test](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) | Test Utility         | Provides utilities for efficiently testing coroutines.                                      |