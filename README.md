# 🛒 Supermarket Billing System

## 📌 Project Overview

The **Supermarket Billing System** is a console-based Java application designed to manage customer queues and generate supermarket bills efficiently.

It allows customers to join a billing queue, purchase products, undo the last scanned item, and generate bills. Product prices are managed using a HashMap, while generated bills are stored in a text file.

## 🚀 Features

- **Add Customer:** Add customers to the billing queue.
- **Customer Billing:** Process customers in FIFO order.
- **Product Management:** Store product names and prices using HashMap.
- **Undo Operation:** Remove the last scanned item using Stack.
- **View Queue:** Display customers waiting for billing.
- **Bill Generation:** Calculate item-wise prices and total amounts.
- **File Handling:** Load previous bills and save new bills in `bills.txt`.

## 🛠️ Technologies & Concepts Used

- Programming Language: Java
- Queue: Customer management
- HashMap: Product price storage
- Stack: Undo functionality
- Array: Bill item storage
- Methods: Modular programming
- File Handling: Reading and writing bill records

## 💰 Product Price List

| Product | Price |
|---|---:|
| Rice | ₹60 |
| Milk | ₹30 |
| Bread | ₹40 |

## 📂 Project Structure

```text
Supermarket-Billing-System/
│
├── Main.java
├── bills.txt
└── README.md
```

## ▶️ How to Run

**Step 1:** Clone the repository.

```bash
git clone https://github.com/YOUR-USERNAME/Supermarket-Billing-System.git
```

**Step 2:** Open the project directory.

```bash
cd Supermarket-Billing-System
```

**Step 3:** Compile the Java program.

```bash
javac Main.java
```

**Step 4:** Run the application.

```bash
java Main
```

## 📋 Main Menu

```text
===== SUPERMARKET BILLING SYSTEM =====

1. Add Customer
2. Bill Next Customer
3. View Queue
4. Exit
```

## 🎯 Learning Outcomes

- Understanding Queue and FIFO operations.
- Implementing Stack for undo functionality.
- Using HashMap for efficient product price lookup.
- Working with arrays and methods in Java.
- Understanding file reading and writing.
- Applying Object-Oriented Programming concepts.

## 👨‍💻 Author

**Atul Kumar**

B.Tech CSE (AI & ML)

## 📜 License

This project is created for educational and academic purposes.
