
<h1 align="center">Thogakade Management System </h1>

Thogakade Management System is a comprehensive application designed to manage retail operations efficiently. The system combines a robust backend developed using Java with an intuitive user interface built using Scene Builder.

## Features
- **Order Management:** Place, update, and manage customer orders.
- **Inventory Management:** Track and update item stock levels in real time.
- **Customer Management:** Manage customer information and purchase history.
- **Database Integration:** Persistent storage of data using a relational database.

## Technologies Used
- **Backend:** Java
- **Frontend:** Scene Builder (JavaFX)
- **Database:** MySQL
- **Build Tool:** Maven 

## Getting Started
### Prerequisites
1. Install Java Development Kit (JDK 11 or later).
2. Install Scene Builder.
3. Set up a MySQL database and configure the connection in the project.
4. Clone this repository:
   ```bash
   git clone https://github.com/SandaruwanWeerawardhana/Thogakade-System-JavaFx.git
   ```

### Installation
1. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).
2. Configure the database connection in the `DBConnection` class.
3. Build and run the project.

### Running the Application
1. Start the database server.
2. Launch the application.
3. Use the GUI to perform operations like managing orders, customers, and inventory.

## Project Structure
- **src/main/java:** Contains all Java source files.
- **src/main/resources:** Contains FXML files and other resources for the UI.
- **Database Scripts:** Located in the `db_scripts` folder for setting up the database schema.

## Screenshots
<div align ="center">
   
![Capture](https://github.com/user-attachments/assets/8144fdc6-3603-45c1-a81f-892606d9bbef)

![5Capture](https://github.com/user-attachments/assets/2d0b61ea-44be-4869-95f9-1c19436ace15)

![3Capture](https://github.com/user-attachments/assets/353033e8-bd1a-456e-b706-b65727fca2ff)

![2Capture](https://github.com/user-attachments/assets/2310a5a9-2868-4e98-b4a6-87fdd4a0e0fc)
</div>

## Database Quary

CREATE DATABASE Thogakade;

USE Thogakade;

CREATE TABLE Customer(
CustID VARCHAR(6) NOT NULL,
Name VARCHAR(30) NOT NULL,
Address VARCHAR(30),
Salary DECIMAL(10,2),
CONSTRAINT PRIMARY KEY (CustID)
);

CREATE TABLE Item(
ItemCode VARCHAR(6) NOT NULL,
Description VARCHAR(50),
UnitPrice DECIMAL(6,2),
QtyOnHand INT(5),
CONSTRAINT PRIMARY KEY (ItemCode)
);

CREATE TABLE Orders(
OrderID VARCHAR(6) NOT NULL,
OrderDate DATE,
CustID VARCHAR(6) NOT NULL,
CONSTRAINT PRIMARY KEY (OrderID),
CONSTRAINT FOREIGN KEY(CustID) REFERENCES Customer(CustID)
);

CREATE TABLE OrderDetail(
ItemCode VARCHAR(6) NOT NULL,
OrderQTY INT(11) NOT NULL,
OrderID VARCHAR(6) NOT NULL,
UnitPrice decimal(10,2),
CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
CONSTRAINT FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode)
);


CREATE TABLE users(
Id int auto_increment not null primary key,
UserName VARCHAR(100) NOT NULL,
Email VARCHAR(100) NOT NULL,
Password varchar(200) NOT NULL
);
