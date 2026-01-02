# 3-Tier Architecture - Banking Application

## Overview
Online banking application implementing 3-tier architecture with separate Presentation, Business Logic (Application), and Data Access layers.

## Files
- **BankingApp.java** - Presentation Tier (UI)
- **BankService.java** - Application Tier (Business Logic)
- **BankDAO.java** - Data Tier (Data Access Object)

## Architecture
```
┌─────────────────────┐
│  Presentation Tier  │  BankingApp.java (UI, Scanner)
│    (User Interface) │
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│  Application Tier   │  BankService.java (Business Logic)
│  (Business Logic)   │
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│     Data Tier       │  BankDAO.java (HashMap database)
│   (Data Access)     │
└─────────────────────┘
```

## Code Explanation

### BankingApp.java (Presentation)
```java
Scanner sc                    → User input
BankService service           → Business logic reference
// Displays menu, takes input, calls service methods
```

### BankService.java (Application)
```java
BankDAO dao                   → Data access reference
checkBalance(accNo)           → Business logic for balance check
deposit(accNo, amt)           → Validates and calls DAO
withdraw(accNo, amt)          → Checks balance before withdrawal
transfer(from, to, amt)       → Handles fund transfer logic
```

### BankDAO.java (Data)
```java
HashMap<String, Double>       → In-memory database
createAccount()               → Inserts new account
getBalance()                  → Retrieves balance
updateBalance()               → Updates account balance
```

## Features
- Create Account
- Check Balance
- Deposit
- Withdraw
- Transfer

## How to Run
```bash
javac *.java
java BankingApp
```

---

## Viva Questions

### Basic Questions
1. **What is 3-tier architecture?**
   - Software architecture pattern with three layers: Presentation (UI), Application (Business Logic), and Data (Database).

2. **What is the Presentation Tier?**
   - User interface layer that displays information and takes user input.

3. **What is the Application Tier?**
   - Contains business logic, rules, and processing between UI and database.

4. **What is the Data Tier?**
   - Handles data storage, retrieval, and database operations.

5. **What is DAO?**
   - Data Access Object - pattern that separates data access logic from business logic.

### Intermediate Questions
6. **Why separate into three tiers?**
   - Separation of concerns, maintainability, scalability, easier testing, can change one tier without affecting others.

7. **What if we put all code in one file?**
   - Tight coupling, hard to maintain, difficult to scale, no code reuse.

8. **How does data flow in 3-tier?**
   - User → Presentation → Service → DAO → Database (and back)

9. **What's the difference between tier and layer?**
   - Tier: Physical separation (different servers)
   - Layer: Logical separation (same or different servers)

10. **Why use HashMap instead of real database?**
    - Simplicity for demonstration; in production, use MySQL, PostgreSQL, etc.

### Advanced Questions
11. **What is MVC vs 3-Tier?**
    - MVC: Model-View-Controller (UI pattern)
    - 3-Tier: Architectural pattern for entire application

12. **How would you add a real database?**
    - Replace HashMap in DAO with JDBC connection to MySQL/PostgreSQL.

13. **What is connection pooling?**
    - Reusing database connections instead of creating new ones each time.

14. **What is transaction management?**
    - Ensuring multiple operations succeed or fail together (ACID properties).

15. **How to handle concurrent access?**
    - Use synchronized methods, database locks, or transaction isolation.

16. **What is dependency injection?**
    - Passing dependencies (like DAO) to service instead of creating inside.
