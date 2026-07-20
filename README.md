# Pagila API

## 📌 Overview

**Pagila API** is one repository within a distributed, multi-repository architecture based on the Pagila sample database schema for PostgreSQL.

This module exposes the **REST API layer** of the Pagila ecosystem. It provides the public interface through which clients interact with the application, orchestrating requests, invoking business services, and returning standardized DTOs.

The project as a whole is designed to model a real-world enterprise architecture by separating responsibilities into dedicated modules (DTO, entity, mapper, and service layers), ensuring scalability, maintainability, and clear boundaries between concerns.

---

## 🎯 Purpose

This repository exists to:

- Expose REST endpoints for the Pagila domain
- Provide a stable API contract for external consumers
- Coordinate requests between clients and the service layer
- Validate incoming requests
- Return standardized DTO responses
- Isolate transport concerns from business and persistence logic

The API layer is primarily responsible for:

- handling HTTP requests
- validating request payloads
- invoking application services
- returning appropriate HTTP responses
- exposing versioned REST resources

---

## 🧱 Project Architecture Context

This repository contains only the **API module** of the Pagila ecosystem.

The full architecture is intentionally split across multiple repositories, where each module is independently versioned, published, and maintained.

Related modules live in separate repositories:

| Module | Responsibility        | Repository                              |
|--------|-----------------------|-----------------------------------------|
| dto    | Data transport        | https://github.com/D3vlin/pagila-dto    |
| entity | Database mapping      | https://github.com/D3vlin/pagila-entity |
| mapper | Object transformation | https://github.com/D3vlin/pagila-mapper |
| api    | REST interface        | https://github.com/D3vlin/pagila-api    |

---

### Architectural Philosophy

Each module is isolated in its own repository to achieve:

- independent versioning
- loose coupling
- clear boundaries
- reusable artifacts
- microservice readiness
- simplified dependency graphs

This repository specifically publishes the **REST interface layer**, providing HTTP endpoints while delegating business and persistence responsibilities to dedicated modules.

---

## 🌐 API Design Principles

The API layer follows these principles:

- RESTful resource design
- Stateless communication
- DTO-only exposure
- Input validation
- Consistent HTTP status codes
- Standardized error responses
- Separation of transport and business logic

The API intentionally avoids:

- exposing JPA entities
- embedding persistence logic
- implementing mapping logic
- containing database access code

All external communication is performed through the **DTO module**, while object transformation is delegated to the **Mapper module**.

---

## 🚀 Why a Dedicated API Module?

Separating the API into its own artifact allows:

- independent API evolution
- reusable business components
- cleaner dependency management
- isolated transport concerns
- easier API versioning
- improved maintainability
- microservice-oriented architecture

This separation enables different applications (REST APIs, GraphQL gateways, messaging services, etc.) to reuse the same underlying business components without duplicating logic.

---

## 📚 Use Cases

This module is intended to serve:

- frontend applications
- mobile clients
- third-party integrations
- API gateways
- external consumers
- testing tools (Postman, Swagger, etc.)

It acts as the public entry point into the Pagila ecosystem.

---

## 🔗 Project Dependencies

This module depends on the shared Pagila components:

- pagila-dto
- pagila-entity
- pagila-mapper

These artifacts provide the transport models, persistence models, and object transformation layers required by the API.

---

## 🧪 Local build workflow

`pagila-api` consumes the shared Pagila artifacts through Maven properties and an opt-in profile for snapshots.

### Release mode

By default, the project resolves the stable published versions declared in `pom.xml`.

### Snapshot mode

To consume the latest internal snapshots, activate the profile:

```powershell
./mvnw clean verify
```

### Local hooks

This repository includes Git hooks under `.githooks`:

- `pre-commit` → compiles with `-Pinternal-snapshots`

Enable them with:

```powershell
git config core.hooksPath .githooks
```

If you prefer system-wide hooks, point `core.hooksPath` to the shared folder you use in your workstation.

### CI/CD policy

This repository follows the same workflow split as the other Pagila modules:

- `ci.yml` → validates the build on `push` and `pull_request`
- `deploy.yml` → publishes the package after a successful `main` build
- `pr-develop-guard.yml` → only allows `feature/*` or `main` into `develop`
- `pr-main-guard.yml` → only allows `develop` into `main`

For the protection to be effective, configure GitHub branch protection so these checks are required before merge.

---

## 🧠 Philosophy

This project is not just a demo.

It is designed to model **real production architecture patterns**, including:

- layered architecture
- modularization
- separation of concerns
- reusable artifacts
- versioned modules
- REST API best practices
- scalable enterprise application design