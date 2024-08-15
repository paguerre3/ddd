# Domain Driven Design
domain driven design architecture compendium


---
Instead of setting the required infrastructure (a.k.a. cloud platform, k8s, docker, etc.) is recommended to establish the design approach, i.e. how to design the solution better so it can be robust over the time.

<b>DDD book by Eric Evans: "Tackling Complexity in the heart of Software"</b> explains how to design a complex software for a complex domain that in the end it doesn't turn into a mess. 
***Note*** the author says it's a design and not an architecture.

Martin Fowler states that <b>Domain Driven Design</b> is an architectural approach to software development that centers the developing on programming a <b>domain model</b> that has a rich <b>understanding</b> of the processes and rules of the development.


---
### Requirements for a DDD approach "in order" (old testament based on original DDD book by Eric Evans):
1. <b>Understand the Domain</b>, i.e. study the domain, speak with stakeholders and product owners that understand processes and rules or the domain.
2. <b>Split the Big-Domain into Sub-Domains</b>, i.e. reducing the overall complexity in smaller pieces that can be tackled separately and then make them to work together.
3. <b>Develop and Ubiquitous Language</b>, i.e. develop a common vocabulary that we agree to use "everywhere", e.g. the same language is used for all stakeholders, product owners and managers. ***Note*** User Stories and Code will be using this words and most of this common language comes from the base Domain, processes and rules.
4. <b>Develop a Domain Model</b>, i.e. write the code that reflects the Domain using ubiquitous/common domain language
5. <b>Separate Domain Model from Implementation Details</b>, i.e. separating the Domain from Infrastructure, frameworks and database details. ***Note*** Domain should stay pure, if written in Java it should only be expressed using Java language (no docker, k8s, db and cloud platform specifications)  


---
<b>Implementing DDD book by Vaughn Vernon</b> provides implementation examples following the original book made by Eric Evans mainly using Java.

A common java framework and technologies involved during implementation are <b>Spring</b> and third party dependencies, i.e.:
- <b>HTTP API</b>: Spring MVC, WebFlux
- <b>Business Logic</b>: @Service
- <b>Messaging</b>: Kafka, RabbitMQ, SQS ...
- <b>HTTP Clients</b>: @HttpExchange, RestClient, OpenFeign
- <b>Caching</b>: Redis, Caffeine
- <b>Persistence</b>: @Repository, JPA, JDBC, MongoDB, ElasticSearch ...

Following the DDD approach the <b>Business Logic/Domain Model</b> becomes the Center of the solution (the "king").

![Screenshot](https://github.com/paguerre3/ddd/blob/main/img/01-domain-as-king.png?raw=true)

The <b>Domain Model</b> encapsulates:
- Domain Knowledge 
- Domain Rules
- Processes 
- Constraints 
- Behaviours 
- State Changes

A <b>Tactical Design</b> includes in the <b>Domain Model</b> the following tactical patterns:
1. Entities
2. Value Objects
3. Repositories
4. Aggregations
5. Factories
6. Domain Services

![Screenshot](https://github.com/paguerre3/ddd/blob/main/img/02-tactical-design.png?raw=true)

Once developed the Domain Model, this is "pure", i.e. it will not specify infrastructure or technology needs. 

DM isn't framework related so it can't be deployed as an artifact.

We have to build something around DM in order to implement it and there isn't only one way of doing it, i.e. it seems like the community standard of implementing DDD is <b>Onion / Hexagonal / Clean</b> architectures which place the DM in the center.

![Screenshot](https://github.com/paguerre3/ddd/blob/main/img/03-direction-of-coupling.png?raw=true)

***Note*** that the interesting thing about this approach is the infrastructure layer a.k.a. persistence depends on the Application Services / Domain Model and not the other way around, i.e. <b>Direction of Coupling is centered on the Domain Model and therefore the Domain Model doesn't adapt to the infrastructure however the infrastructure is coupled to the Application Services of the Domain Model</b>   


