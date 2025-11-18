Overview

This application, developed in Kotlin for Android, is an essential tool for Japanese and Chinese language students. It analyzes input text and generates a ranked list showing the frequency of each Kanji (漢字) or Hanzi (汉字) character.

This functionality allows students to easily identify and prioritize learning the most common characters, which appear at the top of the list, or explore less common ones at the bottom.

Example Usage
Japanese Text Analysis

    Input: "日本の本です。" Output: [本 2], [日 1], [の 1], [で 1], [す 1]

Chinese Text Analysis

    Input: "我是我，你是谁？我不知道我是谁！" Output: [我 4], [是 3], [谁 2], [你 1], [不 1], [知 1], [道 1]

Technology Stack

This project is built using modern Android development practices and libraries:

Technologies Used

    MVVM (Model-View-ViewModel) - Architecture
    Jetpack Compose - UI Toolkit
    Compose Navigation - Navigation
    Hilt - Dependency Injection
    Kotlin Coroutines - Asynchronous Ops
    Room - Local Data Storage:

    Unit4 - Unit Testing
    Mockk - Mocking
    Truth - Assertion
