# Distributed Task Queue System

A reliable, production-grade task queue built from scratch with Java, Spring Boot, and PostgreSQL.

## What It Does

Accept long-running tasks via API → Store in database → Multiple workers process them in parallel → Failed tasks automatically retry.

## How It Works

Client → POST /tasks → Database (PENDING) → Worker picks up → Executes → Updates status (COMPLETED/FAILED)
↓
If fails, retry 3x

## Architecture

- **API Layer** — REST endpoints to submit and check tasks
- **Service Layer** — Business logic for task submission, processing, retries
- **Database** — PostgreSQL with `tasks`, `workers`, `task_logs` tables
- **Workers** — Background processes that poll DB and execute tasks

## Tech Stack

- Java 17+
- Spring Boot 3.x
- PostgreSQL
- Maven

## Getting Started

```bash
# Clone repo
git clone <repo>
cd task-queue

# Install PostgreSQL
# Create database

# Run Spring Boot
mvn spring-boot:run

# Start workers
java -jar worker.jar
```

## Usage

**Submit a task:**

POST /tasks
{
"payload": "send email to user@gmail.com"
}


**Check status:**

GET /tasks/1


**Retry a failed task:**

POST /tasks/1/retry


## Features

✅ Multiple concurrent workers
✅ Automatic retries on failure
✅ Exactly-once delivery semantics
✅ Task history and audit logs
✅ Worker health monitoring

## Learning

Built to understand:
- Database design and transactions
- Concurrent processing
- Fault tolerance and retries
- Distributed systems basics
