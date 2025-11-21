# CP3406 Assignment 2 – Book Tracker App
**Developer:Yuyang Zhou
**Subject:** CP3406 – Mobile App Development

---

## Project Location

The full Android Studio project for Assignment 2 is located in:

```
Book_Tracker_CP3406_Assignment2-YuyangZhou/
```

To run the app:
1. Open this folder in **Android Studio**
2. Wait for Gradle to sync
3. Choose a device/emulator
4. Click **Run ▶**

---

##  Background

This application is the final deliverable for **CP3406 Assignment 2**.
It extends the earlier **Book Tracker** concept from Part A & Part B into a complete **Study Tracker App**.

Books are treated as one type of learning resource within the overall study system, making this project an evolution of the original idea rather than a different topic.

---

##  App Overview

The **Study Tracker App** helps students:

- Manage study tasks
- Track daily study progress
- Record study sessions
- Organise courses and deadlines
- View weekly analytics
- Navigate between dashboard, tasks, calendar, and analytics
- Track learning resources (including books)

It demonstrates practical skills in modern Android development using Kotlin and Jetpack Compose.

---

##  Technologies Used

### **Core Technologies**
- Kotlin
- Jetpack Compose (declarative UI)
- Material 3 Components

### **Architecture**
- MVVM Architecture
- ViewModel + UI State
- Repository Pattern

### **Data Layer**
- Room Database
- DAO / Entity / Database
- Coroutines for background work

### **Dependency Injection**
- Hilt (Dagger-Hilt)

### **Navigation**
- Navigation Compose (multi-screen navigation)

### **Testing**
- Unit Tests
- UI/Instrumented Tests

---

## Main Features

### Dashboard
Displays today's tasks, study summaries, and quick actions.

### Tasks
Add, edit, update, and complete study tasks.

### Calendar
View study sessions and tasks by date.

### Analytics
Charts showing weekly study time, task progress, and trends.

### Study Resources
Books and study materials tracked as part of the learning process.

---

## Folder Guide

Your main Android source code is inside:

```
Book_Tracker_CP3406_Assignment2-YuyangZhou/app/src/
```

Includes:

- `ui/` – Compose screens
- `viewmodel/` – MVVM ViewModels
- `data/` – Entities, DAO, Repository, Room database
- `navigation/` – Navigation graph + routes
- `analytics/` – Data visualisation + logic
- `calendar/` – Calendar screens and date logic

---

##  Promotional Video & Reflection

The required **3–5 minute MP4 promotional video**
and the **500-word PDF self-reflection**
are submitted separately in **LearnJCU**, as required by the assignment instructions.

These files are intentionally **not included** in the GitHub repository.

---

