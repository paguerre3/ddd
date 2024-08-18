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

***Note*** that the interesting thing about this approach is the infrastructure layer a.k.a. persistence depends on the Application Services / Domain Model and not the other way around, i.e. <b>Direction of Coupling is centered on the Domain Model and therefore the Domain Model doesn't adapt to the infrastructure however the infrastructure is coupled to the Application Services of the Domain Model</b> (meaning that if the Domain Model changes then it impacts above layers like infrastructure that will require to adapt to the changes)  

[Onion architecture for implementing DDD](https://dev.to/barrymcauley/onion-architecture-3fgl) 

1. <b>Infrastructure</b>: is where database, file system, or any external web service we depend on live -RestClients.
2. <b>Test</b>: unit, integration and end-to-end (how we validate our business cases).
3. <b>User Interface</b>: is how the User interacts with the code we have built (probably via a Web Application using HTTP Rest or calling another transportation mechanism of the Application Services layer).
4. <b>Application Services</b>: The 3 initial layers are the ones that interact with our "application core", i.e. the <b>Application Services</b> layer a.k.a. the Transport Layer. Within this layer we define what our services can do through a series of contract -HTTP API, Messaging.
5. <b>Domain Service</b>: is where the majority of the business logic exists, it carries out the operations to turn A into B, input into output, egg into chicken. It achieves this through interacting with the final layer -@Service, business logic and adapters of DM.
6. <b>Domain Model</b>: is the representation of the high level data objects we use -@Entity, Value Objects and Repositories.


---
<b>Anemic Domain</b> implementations have Entities that look that will implement domain logic however they don't, i.e. having just accessors probably using Lombok annotations (named entities but only Data containers or Models in MVCs) -@Data @Entity.

The previous approach serves for simple project or legacy projects that doesn't grow in complexity (legacy MVCs).


---
## Domain Driven Design


---
### 1. Understand the Domain
<b>DDD</b> approach requires studying the business, talk with POs, and as result, writing <b>User Stories</b> using a common language for defining the Domain, e.g. USs in case of a library application following DDD:

- As a librarian I want to register a copy of a book available to lend, by scanning book ISBN and the bar code.
- The bar code is printed in the library and pasted on each copy of the book. Each copy has its own unique bar code.
- If the book is not yet in the catalog, it should be added there with information about the title and ISBN.
- As a library user, I want to return a copy of a book I borrowed. If the book is returned after the return date, user must pay a fee.
- As a library user, I want to borrow a copy of a book. I want to know up until when I can hold the book so that I don't pay a fee.

***Note of Dependencies*** 
- There is already another company implementing a service that calculates the fee for late returns.
- To avoid manual work, we are going to use 3rd party service that returns book information based on the ISBN code, e.g.: https://openlibrary.org


--- 
### 2. Split the Big Domain into Sub Domains
Break the "big" Library Domain into 2 "smaller" Sub-Domains, i.e.: 
1. <b>Catalog</b> that the people use to Search for the Books, Browse details and see if there is availability.
2. <b>Lending Books</b> in which teh the book is returned late then there is the need to pay a fee calculated.


--- 
### 3. Ubiquitous Language
<b>Ubiquitous words</b> used to define the common language of the Domain Model are: register copy of a book, lend, ISBN, bar code, catalog, book title, return a copy of a book, return date, fee, borrow a copy of a book


---
### 4. Develop a Domain Model
Implementation using <b>Spring Modulith</b> that will help creating boundaries of the smaller pieces / Sub-Domains defined, e.g. the 1st module will be the Catalog and the 2nd one will be the Lending

***Impl. Notes*** 
- ISB should be treated as a Java record and therefore is immutable, and it will be validated.
- Create BookId Java record as an aggregator inside Book class that holds the logic for generating the UUID and also doing the validation of the BookId a.k.a. value container aggregator.
- ⚠️ A <b>Repository</b> in the Domain doesn't act like Data Access Object (DAO) instead is a Collection of Aggregates or Domain Object (DOs) Entities.
- ⚠️ Some Authors say that <b>Domain Objects should be agnostic to the Underlying Persistence Layer</b> when implementing DDD therefore in this case they suggest <b>having a JPARepository inside the Infrastructure layer responsible for doing the mapping for saving DOs to Data Persistence Entity and vice versa when fetching Persistence Entity to DOs</b>. <b>The mentioned approach is considered "pure" but a Boilerplate problem when the project grows exponentially having the need of doing "multiple mappings" of similar objects</b>, i.e. DOs to DAOs, DAOs to DOs.
  ![Screenshot](https://github.com/paguerre3/ddd/blob/main/img/04-jpa-repo-inside-infra.png?raw=true)
- Each individual <b>Copy</b> of the Book has a unique <b>BarCode</b>.
- ⚠️ Traditionally, inside <b>BookCopy</b> DAO we'll have <code>@ManyToOne</code> JPA annotation with reference to a Book object, but <b>in DDD approach it isn't suggested to place ManyToOne object references when adding Aggregates or Entities inside each other having different DO Repositories</b>, i.e. because Book has its own DO Repository and BookCopy also has its own DO Repository then Book and Copy are considered separated aggregates. The <b>proper aggregate rule is to reference by id so the DO container doesn't have the ability of changing the state of the reference DO aggregation</b>, i.e. add bookId reference to the DO BookCopy so we don't provide to the BookCopy DO the capability of updating the state of Book DO.
- Hibernate requires default empty constructors not used by DDD approach that could be set with default or private visibility no accessed outside the package or class (again, NOT used by DDD approach, only for code functioning).
- ⚠️ In a legacy approach under the Application layer we'll have multiple services each one having multiple methods holding common business behaviour that will result in multiple methods assigned to a Service and in the end it will be difficult to maintain relations when code grows in complexity, i.e. having a Single service class with multiple business methods related with being hard to scale-up and doing test maintenance (a.k.a. God "service" classes, e.g. BookService and CopyService)  but <b>in Clean Architecture is recommended to have a "single" Class per Use Case</b>, i.e. taking each possible business method of the Service class and put it in a separated class (easier to maintain in terms of loosely coupling each Use Case in isolation and for doing TDD)
- Having value objects as arguments like BookId and CopyId records is useful for avoiding flipping then if they were Strings.