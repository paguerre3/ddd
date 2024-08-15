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
![Screenshot](https://github.com/paguerre3/fqoperation/blob/master/design/pckge-diagram.png?raw=true)