# 3-Tier Architecture - E-Commerce Application

## Overview
E-commerce website implementing 3-tier architecture with product catalog, shopping cart, and checkout functionality.

## Files
- **ECommerceApp.java** - Presentation Tier (UI)
- **ECommerceService.java** - Application Tier (Business Logic)
- **ECommerceDAO.java** - Data Tier (Data Access)

## Architecture
```
┌─────────────────────┐
│  Presentation Tier  │  ECommerceApp.java
│  (User Interface)   │  - Login, View Products, Cart, Checkout
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│  Application Tier   │  ECommerceService.java
│  (Business Logic)   │  - Add to cart, Checkout logic
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│     Data Tier       │  ECommerceDAO.java
│   (Data Access)     │  - Products, Prices, Cart storage
└─────────────────────┘
```

## Code Explanation

### ECommerceApp.java (Presentation)
```java
// Guest menu: Login, View Products, Exit
// User menu: View Products, Add to Cart, View Cart, Checkout, Logout
```

### ECommerceService.java (Application)
```java
addProduct(id, name, price)  → Add product to catalog
viewProducts()               → Display all products
addToCart(user, productId)   → Add item to user's cart
checkout(user)               → Process order, clear cart
```

### ECommerceDAO.java (Data)
```java
HashMap<Integer, String> products   → Product ID to name
HashMap<Integer, Double> prices     → Product ID to price
HashMap<String, List<Integer>> cart → User to cart items
```

## Features
- User Login
- View Product Catalog
- Add to Shopping Cart
- View Cart with Total
- Checkout

## How to Run
```bash
javac *.java
java ECommerceApp
```

---

## Viva Questions

### Basic Questions
1. **What is E-Commerce?**
   - Electronic commerce - buying and selling goods/services over the internet.

2. **What data structures are used?**
   - HashMap for products, prices, and shopping cart.

3. **How is user session handled?**
   - User variable stores logged-in username; null means logged out.

4. **What does checkout do?**
   - Displays cart, calculates total, places order, clears cart.

5. **Why separate DAO from Service?**
   - DAO handles data storage; Service handles business rules.

### Intermediate Questions
6. **How would you add product inventory?**
   - Add quantity field in DAO, decrement on purchase, check availability.

7. **How to implement user registration?**
   - Add users HashMap in DAO with username/password.

8. **What is a shopping cart?**
   - Temporary storage of items user intends to purchase.

9. **How to calculate total price?**
   - Iterate cart items, sum up prices from prices HashMap.

10. **How would you add payment processing?**
    - Create PaymentService with payment gateway integration.

### Advanced Questions
11. **How to handle concurrent cart access?**
    - Use synchronized collections or database transactions.

12. **What is session management in web apps?**
    - Maintaining user state across multiple HTTP requests using cookies/sessions.

13. **How to implement order history?**
    - Create Orders table/HashMap storing completed orders per user.

14. **What is product recommendation?**
    - Suggesting products based on browsing/purchase history.

15. **How to scale e-commerce application?**
    - Load balancers, distributed databases, caching (Redis), microservices.

16. **What security concerns exist?**
    - SQL injection, XSS, CSRF, secure payment, password hashing.
