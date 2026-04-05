### About
Basic Java/Springboot project for personal upskilling.

### Learnings
**Springboot**
- Entity: Is a blueprint of a database model, although logic specific to the model can live here, its not the best practise
  - Accessing / updating attributes are strictly done via getters/setters - properties are private by convention
- Repository: Provides some database methods for a specific Entity. Such as `findById`
- Service: A service class is used to implement the business logic for a specific Entity
- Controlelr: Is the api endpoint for a specific entity, it delegates request data to the service layer
- DTO: Separates API request/response shape from entity

**Docker**

It allows you to containerise your project, in this case a springboot api, into a single portable unit. With java and MySQL configured, without needing to manually setup each one.

We can use Docker Compose to orchestrate multiple containers; the app + MySQL. Volumes are used to persist data between container restarts, allowing MySQL data to survive `docker-compose down`.