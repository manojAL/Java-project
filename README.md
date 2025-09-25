# Campus Course & Records Manager (CCRM)

# Project Overview:
Campus Course & Records Manager (CCRM) is a comprehensive console-based Java application designed for educational institutes to manage students, courses, enrollments, and grades. The system demonstrates advanced Java programming concepts including OOP principles, design patterns, exception handling, file I/O operations, and modern Java features like Streams API and Date/Time API.

# Key Features:

Student Management (Add/Update/List/Deactivate)

Course Management with search and filter capabilities

Enrollment System with business rules

Grade Management and GPA calculation

Report Generation and Transcripts

Data Import/Export functionality

JDK Version: Java 17 or higher recommended

How to Run:

Method 1: Using Eclipse IDE
bash
1. Import project into Eclipse
2. Right-click on CampusCourseRecordsManager.java
3. Select "Run As" → "Java Application"
Method 2: Command Line
bash

javac CampusCourseRecordsManager.java
java CampusCourseRecordsManager
# Evolution of Java
1995: Java 1.0 released by Sun Microsystems

1997: Java 1.1 introduced inner classes and JDBC

2000: Java 1.3 brought HotSpot JVM

2004: Java 5 introduced generics, annotations, autoboxing

2014: Java 8 added Lambda expressions and Stream API

2017: Java 9 introduced modules (Project Jigsaw)

2021: Java 17 LTS released with sealed classes

2023: Java 21 introduced virtual threads
# JDK/JRE/JVM Explanation
JVM (Java Virtual Machine)

Provides runtime environment for Java bytecode

Platform-dependent (different for Windows, Linux, Mac)

Responsible for memory management and garbage collection

JRE (Java Runtime Environment)

= JVM + Core Libraries

Required to run Java applications

Does not include development tools

JDK (Java Development Kit)

= JRE + Development Tools (compiler, debugger, etc.)

Required to develop Java applications

Used in this project for development
<img width="875" height="580" alt="Screenshot 2025-09-25 215243" src="https://github.com/user-attachments/assets/0ce89698-feab-4a72-93cb-d864ae265bff" />

# Windows Installation & Eclipse Setup

# Java Installation Steps:
Download JDK from Oracle website

Run installer and follow setup wizard

Set JAVA_HOME environment variable:

text
JAVA_HOME=C:\Program Files\Java\jdk-17
Add to PATH:

text
PATH=%JAVA_HOME%\bin;...
Verify installation:

bash
java -version
javac -version
# Eclipse Setup Steps:
Download Eclipse IDE for Java Developers

Extract to preferred location

Launch eclipse.exe

Create workspace for CCRM project

Import existing project or create new Java project
# Testing Commands Within Application:
text
=== MAIN MENU ===
1. Student Management
2. Course Management
3. Enrollment Management
4. Grade Management
5. Reports
6. Exit

Sample Test Flow:
1 → 1 (Add Student) → S001, 2024CS001, John Smith, john@uni.edu
2 → 2 (List Courses) → View sample courses
3 → 1 (Enroll Student) → S001, CS101
4 → (Record Grades) → S001, CS101, 85
5 → 2 (Transcript) → S001
