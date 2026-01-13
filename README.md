# 🛒 Market-Mate

> A lightweight, full-stack Point of Sale (POS) application built for streamlined transaction handling.


---

## 📖 About
**Market-Mate** is a Java-based Point of Sale system designed for small businesses or pop-up stalls. It simplifies the checkout process by managing inventory and calculating cart totals instantly.

Unlike complex e-commerce gateways, Market-Mate is designed for **manual payment processing**. The system calculates the final transaction amount, which the operator then enters into an external physical card reader or cash register.

---

## 🛠 Tech Stack

| Component | Technology |
| :--- | :--- |
| **Frontend** | Vaadin (Java UI Framework) |
| **Backend** | Java |
| **Database** | SQLite |
| **Build Tool** | Maven |

---

## ✨ Features

* **📦 Inventory Management:** easy-to-use interface for adding, deleting, and updating product details and pricing.
* **🛒 Point of Sale Interface:** Fast "Add to Cart" functionality with automatic total calculation (taxes/subtotal).
* **💳 Manual Payment Workflow:** Optimized display of final totals for quick entry into external card terminals.
* **🛡️ Secure Admin Dashboard:** Password-protected area for sensitive operations like modifying base prices or stock levels.

---

## 🚀 Run Locally

Follow these steps to get the project running on your local machine.

### Prerequisites
* Java JDK (Version 17 or higher recommended)
* Maven (optional, as the project includes a wrapper)

### Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/Durovo51/Market-Mate.git](https://github.com/Durovo51/Market-Mate.git)
    cd Market-Mate
    ```

2.  **Build the project**
    *(Using the included Maven wrapper)*
    ```bash
    ./mvnw clean install
    ```
    *(Note: On Windows, use `mvnw.cmd clean install`)*

3.  **Run the application**
    ```bash
    ./mvnw spring-boot:run
    ```
    *Or, if you are not using Spring Boot, use:* `mvn jetty:run`

4.  **Access the App**
    Open your browser and navigate to: `http://localhost:8080`

---

## 👤 Author

* **Jake Bryant** - [GitHub Profile](https://github.com/Durovo51)

---

## 📄 License

This project is open source and available under the [MIT License](https://github.com/Durovo51/Market-Mate/blob/main/LICENSE.txt).
