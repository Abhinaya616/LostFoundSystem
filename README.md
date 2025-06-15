# 🧾 Lost and Found System

A **console-based Java application** to help campuses and offices manage lost and found items efficiently. This project uses **Java, MySQL, and JDBC** and provides functionality to report, view, and match lost and found items.

---

##  Technologies  Used

- Java (JDK 8+)
- MySQL (RDBMS)
- JDBC (Java Database Connectivity)
- Console-based user interface

---

##  Database Schema

>>> Table: `lost_items`

| Column Name     | Data Type     | Description                       |
|------------------|----------------|-----------------------------------|
| id               | INT (PK)       | Auto-incremented primary key      |
| person_name      | VARCHAR(100)   | Name of person who lost the item |
| item_name        | VARCHAR(100)   | Name of the lost item            |
| description      | TEXT           | Description of the item          |
| location_lost    | VARCHAR(100)   | Where the item was lost          |
| date_lost        | DATE           | Date of loss                     |
| status           | VARCHAR(50)    | 'Unresolved' / 'Matched'         |

>>>Table: `found_items`

| Column Name              | Data Type     | Description                            |
|---------------------------|----------------|----------------------------------------|
| id                        | INT (PK)       | Auto-incremented primary key           |
| finder_name               | VARCHAR(100)   | Name of the person who found the item  |
| item_name                 | VARCHAR(100)   | Name of the found item                 |
| description               | TEXT           | Description of the item                |
| location_found            | VARCHAR(100)   | Where the item was found               |
| date_found                | DATE           | Date item was found                    |
| status                    | VARCHAR(50)    | 'Unmatched' / 'Matched'                |
| matched_to_lost_item_id   | INT (FK)       | Links to matched lost item (if any)    |


------FEATURES------


- Report a **lost item** with details.
- Report a **found item** with details.
- **View** all reported lost or found items.
- Automatically **match** lost and found items based on:
  - Item name
  - Description
  - Location
- Update status of matched items to "Resolved"/"Matched".
- Store data persistently using **MySQL database**.


------JDBC CONNECTION------

Make sure your DBConnection.java file has the correct credentials:
private static final String URL = "jdbc:mysql://localhost:3306/lost_found_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";


------SETUP ECLIPSE-------

If you're using an IDE:
1> Open the project in Eclipse/IntelliJ

2> Add the MySQL JDBC driver to your project:

3> Right-click project > Build Path > Add External JARs.Add mysql-connector-java-8.0.xx.jar

4> Run MainApp.java


------📁 Clone the Repository-----
```bash
git clone https://github.com/your-username/lost-found-system.git
cd lost-found-system

