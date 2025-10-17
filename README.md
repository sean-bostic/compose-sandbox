# Compose Previews Sandbox
A multiplatform focused sandbox to allow quick prototyping and debugging using `Compose Preview`. My intentions with this sandbox is to give myself a project angostic structure to use mock data to _quickly_ create UI designs with Compose with a focus on reusable compasables that can be used seamlessly for iOS, Desktop, and Android. This repository is meant for Compose Previews **only** that are freestanding Composables using mock data.


<img width="1783" height="935" alt="image" src="https://github.com/user-attachments/assets/922e3d9d-ea42-4bbd-ae3f-4e6faa454f1d" />


## General Structure and How-To Use
- Open `compose-sandbox` in Android Studio and perform Gradle Sync
- Create a new package for each new feature and place mock data objects and composables in the `shared/src/commonMain/kotlin/<feature-name-here>` path
- Create a simple preview in `androidApp/src/main/kotlin/` with the `@Preview` annotations for each desired mutation of the UI component
- Run one of the previews and enable `Live Edit` on Android Emulator to see hot reloaded changes for debugging.
- Compose Preview from the gutter and `Build & Refresh` on every code change for the composable allows for a more standalone and lightweight preview option.

Sample Preview Setup
```kotlin
@Preview(name = "Pumping 🚀🚀", showBackground = true)
@Composable
fun XrpPriceCardPumpingPreview() {
  MaterialTheme {
     XrpPriceCard(MockData.pumpingXrp)
  }
}
```

Project Structure for standalone compose creations.

<img width="795" height="360" alt="image" src="https://github.com/user-attachments/assets/2bcda34b-f880-42f9-8f2e-c7abfc627dec" />
