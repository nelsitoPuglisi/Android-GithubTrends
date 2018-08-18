# Android-GithubTrends
MVI - Clean implementation on Android, using Github public api

## Diagrams

Paste each diagram file content into https://www.websequencediagrams.com/

### Notes

1. Legibility is important, so I didn't extract `dependencies` because I have one module only. In case of adding more modules, I would use this approach: [buildsystem](https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/master/buildsystem/dependencies.gradle)
2. Ref.: COMPONENT COUPLING (Clean Architecture)
    > Component dependency diagrams have very little do to with describing the function of the application. Instead, they are a map to the buildability and maintainability of the application.

    So I did't add any module because wasn't needed on the scope of this project.
    But I know that modularization brings a great benefit in build time (like [this](https://medium.freecodecamp.com/how-modularisation-affects-build-time-of-an-android-application-43a984ce9968) article explain),
    developer experience, and at estimating new features.
