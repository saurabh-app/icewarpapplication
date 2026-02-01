IceWarp  Android App


A modern Android application built using Kotlin, MVVM, RxJava, Coroutines, Retrofit, SQLDelight, XML Views, and Jetpack Compose.
The app authenticates a user, stores the auth token locally, fetches channels from the IceWarp API, and displays them in a grouped list.

🚀 Features
✅ Screen 1: Login Screen (XML)

Login using email & password

Input validation:

Fields cannot be empty

Valid email format

Password ≥ 6 characters

API call: iwauthentication.login.plain

Saves auth token securely in SQLDelight

Built using RxJava + MVVM

✅ Screen 2: Channels Screen (Jetpack Compose)

Fetches token from local database

API call: channels.list

Saves channels locally using SQLDelight

Displays channels using Jetpack Compose

Grouped by groupFolderName (Bonus feature)

Toolbar with Back Navigation
