# Hotel Booking System Project

## 1. Introduction

### Project Overview
The Hotel Booking System project is a Java-based application that demonstrates the implementation of various design patterns in software development. It aims to provide a flexible and modular solution for managing hotel bookings, incorporating design patterns to enhance scalability, maintainability, and reusability.

### Purpose and Objectives
The primary purpose of the project is to showcase the application of design patterns in solving real-world problems. The main objectives include:
- Implementing and illustrating the use of design patterns such as Singleton, Observer, Factory, Decorator, Adapter, and Strategy.
- Building a comprehensive hotel booking system with features like room selection, additional services, and pricing strategies.
- Demonstrating the importance of proper software design in creating maintainable and extensible systems.

## 2. Project Requirements

### Comprehensive Documentation
The project documentation provides detailed explanations of each implemented feature and design pattern. It serves as a guide for developers and collaborators to understand the system's architecture.

### UML Diagrams
UML diagrams illustrate the structure of the design patterns used in the project. These diagrams include class diagrams, sequence diagrams, and any other relevant diagrams to visually represent the relationships and interactions within the system.

### User-Friendly Interface
The project includes a user-friendly command-line interface (CLI) to interact with the application. The CLI demonstrates the functionalities enabled by the implemented design patterns, allowing users to make bookings, add services, and experience the flexibility of the system.

## 3. Package Descriptions

### `adapter` Package
- **Purpose:** Contains classes related to the Adapter design pattern.
- **Content:**
    - `BookingPayment`: Interface defining the contract for making payments.
    - `CreditCardPayment`: Class implementing the `PaymentSystem` interface for credit card payments.
    - `Kaspi Payment`: Class implementing the `PaymentSystem` interface for credit card payments.
    - `CreditCardPaymentAdapter`: Adapter class adapting `CreditCardPayment` to the `BookingPayment` interface.
    - `KaspiPaymentAdapter`: Adapter class adapting `CreditCardPayment` to the `BookingPayment` interface.

### `db` Package
- **Purpose:** Handles database operations and entities.
- **Content:**
    - `DatabaseManager`: Singleton class managing database connections and operations.
    - `User`: Class representing a user entity with basic information.
    - `BookingDB`: Class representing a booking entity with details stored in the database.

### `decorator` Package
- **Purpose:** Implements the Decorator design pattern to add features to room bookings.
- **Content:**
    - `BookingDecorator`: Abstract class providing a base for booking decorators.
    - `BookingInterface`: Interface defining methods for booking details.
    - `BreakfastDecorator`: Concrete decorator adding breakfast to a room booking.
    - `ExcursionDecorator`: Concrete decorator adding an excursion to a room booking.
    - `TransferDecorator`: Concrete decorator adding an excursion to a room booking.

### `factory` Package
- **Purpose:** Contains classes related to the Factory design pattern for creating rooms.
- **Content:**
    - `Room`: Interface defining methods for room details.
    - `RoomFactory`: Interface for creating rooms.
    - `StandardRoom`: Concrete class representing a standard room.
    - `StandardRoomFactory`: Factory class for creating standard rooms.
    - `LuxuryRoom`: Concrete class representing a luxury room.
    - `LuxuryRoomFactory`: Factory class for creating luxury rooms.
    - `FamilydRoom`: Concrete class representing a standard room.
    - `FamilyRoomFactory`: Factory class for creating standard rooms.

### `observer` Package
- **Purpose:** Implements the Observer design pattern for notifications.
- **Content:**
    - `Observer`: Interface defining the method for receiving notifications.
    - `HotelManager`: Singleton class managing bookings and notifying rooms.

### `singleton` Package
- **Purpose:** Contains the Singleton design pattern implementation.
- **Content:**
    - `HotelManager`: Singleton class managing bookings.

### `strategy` Package
- **Purpose:** Implements the Strategy design pattern for pricing strategies.
- **Content:**
    - `PricingStrategy`: Interface defining the contract for pricing strategies.
    - `DiscountPricingStrategy`: Concrete class implementing a discount pricing strategy.
    - `StandardPricingStrategy`: Concrete class implementing a standard pricing strategy. 
    - `LastMinutePricingStrategy`: Concrete class implementing a standard pricing strategy.

## 4. Conclusion

### Summary
In summary, the Hotel Booking System project successfully integrates various design patterns to create a modular and extensible solution for managing hotel bookings. The implemented patterns enhance code organization, reusability, and scalability.

### Challenges Faced
During the project, several challenges were encountered, including:

1. **Integration of Design Patterns:**
  - *Challenge:* Ensuring seamless integration of multiple design patterns, such as Singleton, Observer, Factory, Decorator, Adapter, and Strategy.
  - *Resolution:* Thorough planning and collaboration among team members, frequent code reviews, and refactoring where necessary.

2. **User Input Management in CLI:**
  - *Challenge:* Effectively managing user input in the command-line interface (CLI) for room selection, additional features, and pricing strategy.
  - *Resolution:* Implementing robust input validation and error handling to provide a user-friendly experience.

3. **Database Operations:**
  - *Challenge:* Implementing database operations for user and booking entities, including user registration and storing booking details.
  - *Resolution:* Iterative development and testing to ensure proper database connectivity, SQL query execution, and data integrity.

4. **Data Retrieval and Display:**
  - *Challenge:* Retrieving and displaying booking information from the database in a user-friendly format.
  - *Resolution:* Refining SQL queries and optimizing database schema to streamline data retrieval, and improving the CLI output for better user comprehension.

5. **Adapting Database Structure:**
  - *Challenge:* Adapting the database structure to accommodate changes in the project requirements and entity relationships.
  - *Resolution:* Version control for database schema, migration scripts, and careful consideration of future requirements during database design.

6. **Testing and Validation:**
  - *Challenge:* Ensuring comprehensive testing and validation of database interactions and the overall system.
  - *Resolution:* Implementing unit tests, integration tests, and manual testing to validate the correctness and robustness of database operations.

These challenges were addressed through a collaborative and iterative development process, promoting effective communication and problem-solving within the project team.

### Future Improvements
Future improvements to the project may include:
- Enhancing the CLI to provide more user-friendly interactions.
- Adding support for a graphical user interface (GUI) to improve accessibility.
- Incorporating additional design patterns or features to further showcase best practices in software development.


## Singleton Pattern:

**Purpose:**
Ensures that a class has only one instance and provides a global point of access to that instance.

**Usage in Project:**
`HotelManager` class is a singleton managing hotel bookings to prevent multiple instances and ensure a centralized booking management system.

## Observer Pattern:

**Purpose:**
Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

**Usage in Project:**
`Observer` interface and `HotelManager` class implement the observer pattern to notify bookings (observers) about events like fire alarms.

## Factory Pattern:

**Purpose:**
Defines an interface for creating an object but leaves the choice of its type to the subclasses, creating instances of classes without specifying their concrete types.

**Usage in Project:**
`RoomFactory` interface and its implementations (`StandardRoomFactory` and `LuxuryRoomFactory`) create instances of rooms without specifying their exact type.

## Decorator Pattern:

**Purpose:**
Attaches additional responsibilities to an object dynamically, providing a flexible alternative to subclassing for extending functionality.

**Usage in Project:**
`BookingDecorator` abstract class and its concrete implementations (`BreakfastDecorator` and `ExcursionDecorator`) add features like breakfast or excursions to room bookings.

## Adapter Pattern:

**Purpose:**
Allows the interface of an existing class to be used as another interface, converting one interface into a compatible one.

**Usage in Project:**
`CreditCardPaymentAdapter` adapts the `CreditCardPayment` class to the `BookingPayment` interface, allowing it to be used where payment processing is expected.

## Strategy Pattern:

**Purpose:**
Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

**Usage in Project:**
`PricingStrategy` interface and its implementations (`DiscountPricingStrategy` and `StandardPricingStrategy`) encapsulate different pricing strategies, allowing them to be interchangeable in the booking process.