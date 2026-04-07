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

**Kubernetes**

Kubernetes is a more advanced way to manage and run docker containers. This is done through pods, where each pod can run 1 or more containers. Kubernetes runs containers across multiple machines, self-heals, scales and load balances — all internally. Some examples:
- Defining `replicas` in the app.yaml, if a replica were to be killed or fails, it will spin up a new pod
- Scaling up or down with `kubectl scale` — can go from 2 replicas to 5 with one command, no redeployment needed
- K8s load balances traffic across all replicas automatically via Services

Some general definitions:
- Image: The built snapshot, like a `.jar` file. It's static, sits on disk
- Container: A running instance of an image. Like running `java -jar app.jar`. It's alive, processing requests, using memory
- Service: A stable network address that routes traffic to pods. Pods are disposable (they get killed, replaced, given new IPs). A Service gives them a permanent name that always routes to the right pod
- Pod: A wrapper around 1 or more containers. Most pods run a single container