# Chat Application      
This project is a REST API for a chat application built using Java 21, Quarkus Framework, and Maven as a build tool. 
The system enables users to register, log in, and communicate with others through direct and group messaging.

# Features   
User Registration & Authentication

A new user can register with the system.
After registration, the user can log in using their credentials.
Authentication is handled via JWT tokens, secured using a public/private key pair.

Messaging System
A user can send messages in three different ways:
To a specific user (direct message).
To all users (broadcast message).
To a group of users (group chat).

Group Management
Users can create groups and manage their members.
The system provides functionality to attach (add) or detach (remove) users from a group dynamically.

# Technology Stack

Java 21 
Quarkus A lightweight Java framework   
Maven (Dependency and build management)   
JWT Authentication (Using public/private keys for security)   
This API ensures efficient user management, secure authentication, and a flexible messaging system that supports individual, group, and broadcast messaging.
