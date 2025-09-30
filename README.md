
```markdown
# 📱 Contact App (Practice Project)

A simple **Contact Management App** built with **Jetpack Compose**, **Kotlin**, and **Room Database** using the **MVVM (ViewModel, Repository, UseCase) architecture**.  
This project is only for practice and learning purposes.

---

## 🚀 Features
- Add new contacts with **Name** and **Phone Number**
- Display a list of all saved contacts
- Store data locally using **Room Database**
- Built entirely with **Jetpack Compose (no XML)**
- **MVVM architecture** with separation of concerns
- Uses **Hilt** for Dependency Injection
- Uses **Kotlin Coroutines & Flow** for async operations

---

## 🛠️ Tech Stack
- **Language:** [Kotlin](https://kotlinlang.org/)  
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose)  
- **Database:** [Room](https://developer.android.com/training/data-storage/room)  
- **Architecture:** MVVM (ViewModel + Repository + UseCase)  
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)  
- **Async:** Coroutines + Flow  
- **Navigation:** Compose Navigation  

---

## 📂 Project Structure

```

app/
├── data/
│   ├── local/
│   │   ├── UserDao.kt
│   │   ├── UserEntity.kt
│   │   └── AppDatabase.kt
│   └── repository/
│       └── UserRepository.kt
│
├── domain/
│   ├── model/User.kt
│   └── usecase/
│       └── AddUserUseCase.kt
│       └── GetUsersUseCase.kt
│
├── presentation/
│   ├── screens/
│   │   ├── UserListScreen.kt
│   │   └── AddUserBottomSheet.kt
│   ├── state/UserState.kt
│   └── viewmodel/UserViewModel.kt
│
├── di/
│   └── AppModule.kt
│
└── MainActivity.kt

````

---

## ⚡ How it Works
1. **Room Database** stores contact data (name & number).
2. **Repository** abstracts data source.
3. **UseCases** handle business logic.
4. **ViewModel** exposes state to UI using `StateFlow`.
5. **Jetpack Compose Screens** observe ViewModel state and display UI.

---

## 📸 Screenshots
*(Add your screenshots here after running the app)*

---

## ▶️ Setup Instructions
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/contact-app.git
````

2. Open in **Android Studio**
3. Sync Gradle files
4. Run the app on emulator/device

---

## 📚 Learning Goals

* Practice **Jetpack Compose UI**
* Understand **MVVM with Room**
* Learn **state management with ViewModel**
* Implement **Hilt DI**
* Explore **Kotlin Coroutines and Flow**

---

## 📝 License

This project is for **learning purposes only** and not intended for production use.

```

---
