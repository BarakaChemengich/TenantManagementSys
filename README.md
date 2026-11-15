# Tenant Management System

An Android application developed to help landlords manage tenant information efficiently. The system allows landlords to register tenants, view tenant records, monitor total rent, and contact tenants directly through phone calls.

## Download

The latest version of the application will be available in the **Releases** section of this GitHub repository.

**Current Version:** 1.0

## Features

- **User Authentication:** Register and log in to access the application.
- **Dashboard:** View the total number of tenants and the combined rent amount.
- **Add Tenants:** Register new tenants by entering their names, phone numbers, and rent amounts.
- **View Tenants:** Display a list of all registered tenants and their details.
- **Room Database:** Store tenant information locally to ensure records remain available after closing and reopening the application.
- **Call Tenants:** Contact tenants directly using the phone dialer.
- **Navigation:** Move between application screens using the navigation drawer and options menu.

## Architecture

The application follows the **Model-View-ViewModel (MVVM)** architecture and uses the **Repository Pattern** to organize application components and manage data.

### Project Structure

- **ui/** – Contains Activities, Fragments and TenantViewModel, which handle the user interface and user interactions.
- **data/** – Contains Tenant, TenantDao, TenantDatabase and TenantRepository, which manage tenant information and database operations.

### Data Flow

Screens → TenantViewModel (LiveData) → TenantRepository → Room Database

The ViewModel communicates with the Repository to access tenant data, while the Repository manages communication with the Room Database. LiveData allows the user interface to observe data changes and update automatically.

## Team Contributions

The project was completed collaboratively, with each team member responsible for a specific development stage.

| Stage | Responsibility | Member | Admission No. | Pull Request |
|---|---|---|---|---|
| 1 | Lead | Baraka Chemengich | 190278 | Repository setup – no separate PR |
| 2 | ViewModel | Michael Maina | 190528 | [PR #1](https://github.com/BarakaChemengich/TenantManagementSys/pull/1) |
| 3 | Room Database | Wairia Michael | 189948 | [PR #3](https://github.com/BarakaChemengich/TenantManagementSys/pull/3) |
| 4 | Screens | Stanley Mutiga | 190056 | [PR #2](https://github.com/BarakaChemengich/TenantManagementSys/pull/2) |
| 5 | Data Integration | Alvin Muchiri | 175883 | [PR #4](https://github.com/BarakaChemengich/TenantManagementSys/pull/4) |
| 6 | Release | Michelle Mengich | 189914 | To be added after release PR creation |

## Version Information

- **Version Name:** 1.0
- **Version Code:** 1
- **Platform:** Android
- **Database:** Room Database
- **Programming Language:** Kotlin

## Installation

1. Open the **Releases** section of this repository.
2. Download the latest APK file.
3. Open the downloaded APK on an Android device.
4. Allow installation if prompted by your device.
5. Launch the Tenant Management System.

## Project Repository

[GitHub – Tenant Management System](https://github.com/BarakaChemengich/TenantManagementSys)