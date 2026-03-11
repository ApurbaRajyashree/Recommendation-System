# Recommendation System

The Recommendation System is a web-based application built with **Spring Boot** that provides personalized note recommendations to users. The system uses a **user-based collaborative filtering algorithm**, where the similarity between users is computed using **cosine similarity** on their note ratings.

The application helps users discover relevant notes that they haven't interacted with, based on the preferences of similar users.

---

## How It Works

1. **User Rating Collection**
   - Users rate notes on a scale (e.g., 1-5 stars).

2. **User-Item Rating Matrix**
   - Each user’s ratings are stored in a matrix format (user vs. notes).

3. **Cosine Similarity Calculation**
   - Similarity between two users \(A\) and \(B\) is calculated using:

   \[
   \text{Cosine Similarity}(A,B) = \frac{A \cdot B}{||A|| \, ||B||}
   \]

   - A higher score indicates that two users have similar note preferences.

4. **Recommendation Generation**
   - Notes liked by similar users but not yet rated by the target user are recommended.
   - Top-K recommendations can be generated based on similarity scores.

---

## Features

- User registration and authentication (Spring Security)
- Role-based access (optional for future versions)
- Note rating system
- Personalized note recommendations using **user-based collaborative filtering**
- Recommendation algorithm implemented in Java using repository data

---

## Technologies Used

Backend:
- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security
- PostgreSQL

Recommendation Algorithm:
- Custom **Cosine Similarity implementation**
- User-Item rating matrix handling

Frontend:
- Thymeleaf templates (for web interface)

Build Tool:
- Maven
