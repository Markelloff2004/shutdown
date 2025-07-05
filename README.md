# Shutdown Timer Application

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Platform](https://img.shields.io/badge/Platform-Windows-lightgrey)

A desktop application that provides a countdown timer with shutdown confirmation dialog for Windows systems.

## Features

- ⏳ 5-minute countdown timer with real-time display
- ✅ YES/NO confirmation dialog
- ⚡ Immediate system shutdown on:
  - YES button click
  - Timer expiration
- 🚫 Graceful cancellation on NO button click
- 🖥️ Works on Windows systems with Java 17+

## How It Works

1. Application starts a 5-minute (300 seconds) countdown
2. Displays a dialog with:
   - Confirmation prompt
   - Real-time countdown display (MM:SS format)
   - YES/NO buttons
3. If user clicks YES or timer expires:
   - System executes immediate shutdown (`shutdown -s -t 0`)
4. If user clicks NO:
   - Timer stops
   - Application exits gracefully

## Installation

### Prerequisites
- Java Runtime Environment (JRE) 17 or later
- Windows operating system

### Steps
1. Download the latest JAR file from releases
2. Place it in your preferred directory
3. Run with:
   ```cmd
   java -jar ShutdownApp.jar
   ```

## Building from Source

1. Clone the repository
2. Compile:
   ```cmd
   javac -d out src/org/example/*.java src/org/example/core/*.java src/org/example/ui/*.java
   ```
3. Create JAR:
   ```cmd
   jar cvfm ShutdownApp.jar manifest.txt -C out .
   ```

## Scheduling with Windows Task Scheduler

To run automatically:
1. Open Task Scheduler
2. Create Basic Task:
   - Trigger: At logon/Daily/etc.
   - Action: Start a program
   - Program: `java.exe`
   - Arguments: `-jar "path\to\ShutdownApp.jar"`
3. Check "Run with highest privileges"

## File Structure

```
src/
├── org/example/
│   ├── core/
│   │   ├── ShutdownController.java
│   │   └── SystemShutdownExecutor.java
│   ├── ui/
│   │   └── ShutdownDialog.java
│   └── Main.java
```

## Troubleshooting

- If shutdown doesn't work:
  - Run as Administrator
  - Check Java version (`java -version`)
  - Verify system permissions
