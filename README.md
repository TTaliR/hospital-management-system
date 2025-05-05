# Hospital Management System

A Java-based desktop application for managing hospital operations such as patient records, nurse assignments, and doctor scheduling. Built using the Swing library and structured using the MVC (Model-View-Controller) pattern.

## Features

- Add, edit, and remove patient, doctor, and nurse records
- Manage appointments and staff schedules
- GUI-based interface for intuitive use
- Data persistence using serialization
- Modular design with clear separation of concerns

## Project Structure

```
src/
├── autopilot/        # Automated control logic
├── control/          # Controllers for application logic
├── enums/            # Enum definitions for roles/types
├── exceptions/       # Custom exception handling
├── model/            # Data models for hospital entities
├── resources/        # Images and static files
├── utils/            # Utility classes and helpers
└── view/             # GUI components and layout
```

## How to Run

### Prerequisites:
- Java JDK 8 or above
- Java-compatible IDE (e.g., Eclipse, IntelliJ)

### Run via JAR:
Download and double-click the `EX3_206540007_315489856.jar` file or run:
```bash
java -jar EX3_206540007_315489856.jar
```

### Run via Source Code:
1. Import the project into your IDE
2. Compile the `src` folder
3. Run the main GUI class from the `view` package (e.g., `MainWindow.java`)

## External Libraries
- `jcalendar-1.4.jar` – for calendar UI components

## Technologies Used
- Java
- Swing GUI
- MVC Design Pattern
- Java Serialization

## License
This project is for educational purposes only.
