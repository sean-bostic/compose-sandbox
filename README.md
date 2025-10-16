# Compose Sandbox
A multiplatform focused sandbox to allow quick prototyping and debugging using Compose Preview. My intentions with this sandbox are to keep it as a place to build with Compose with a focus on reusable compasables that can be used seamlessly for iOS, Desktop, and Android. If there are more efficient ways to get faster loaded compose changes, I'd be very keen to learn them.

## General Structure and How-To Use
- Open `compose-sandbox` in Android Studio and perform Gradle Sync
- Create a new package for each new feature and place mock data objects and composables in the `shared/src/commonMain/kotlin/<feature-name-here>` path
- Create a simple preview in `androidApp/src/main/kotlin/` with the @Preview annotations for each instance of the mock data object.
- Run Preview from the gutter and `Build & Refresh` on every code change for the composable

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
┌─────────────────────────────────────┐
│  androidApp/                        │
│  ├── ProductPreviews.kt             │  ← ONLY for previewing
│  ├── DashboardPreviews.kt           │  ← Best to be opened in Android Studio to get Compose Preview
│  └── ...Previews.kt                 │  ← @Preview annotations
└─────────────────────────────────────┘
│
│ imports from
↓
┌─────────────────────────────────────┐
│  shared/                            │
│  ├── ProductComponents.kt           │  ← Works on all platforms
│  ├── ProductMocks.kt                │  ← iOS, Android, Desktop
│  ├── DashboardComponents.kt         │  ← Pure Compose
│  └── ...                            │  ← No platform specific code
└─────────────────────────────────────┘