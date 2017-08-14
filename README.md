# SaaS

This project demonstrates an approach to the creation of a multi-tenant [CUBA](https://www.cuba-platform.com) application.

## Objective

Let’s suppose that our CUBA application should be used by several clients:

* There are two types of entities in the application:
    * Shared entities that are available for all clients (typically they represent some reference data). In this project, it is the `PaymentMethod `entity.
    * Client entities - their instances belong to one certain client. In this project, these are `Customer` and `Order` entities.
* Client's users can see only their client entities.
* There is a predefined set of client's user roles:
    * Admins can create users within their client and assign available roles to them. Admins do not see other clients and their users.
    * Regular users can only work with data.

## Solution

Open the project in CUBA Studio, execute *Run > Create database*, then *Run > Start application server* and open the application at `http://localhost:8080/app`.

* Log in as `admin` / `admin`. This account gives the full access to the application and does not belong to any client.

    First, open the *Administration > Access Group* screen.

    The groups under the Clients group represent clients. Each of the client groups has a unique session attribute called `client_id`. This ID will be used to separate client entities.

    Open the *Constraints* tab for the Clients group. It contains restrictions that will be applied when reading entities by users located in the client's groups. All client entities (`Customer` and `Group` in this project) should have the constraint with the following `where` clause: `{E}.client = :session$client_id`. Security-related entities have special restrictions to satisfy requirements of having client admins that are able to manage their own users.

    The `admin` user can create only `PaymentType` entities shared between clients. If you try to create a client-specific entity like `Customer`, you will get an exception.

* Log in as `stark` / `1`. This is an admin of the `Stark Industries` client. You can create customers and orders, as well as create users for your client and assign the roles `client_admin` or `client_user` to them.

* Log in as `potts` / `1`. This is a user of the `Stark Industries` client. You can see and manage the customers and orders created by `stark`, but you cannot create new users.

* Log in as `dent` / `1`. This is an admin of the `Sirius Cybernetics Corp.` client. You can create users, customers and orders, but you don't see entities created by the `Stark Industries` users.

## Implementation Details

1. The `StandardClientEntity` entity is a `@MappedSuperlass` and serves as a base class for all client entities. It has the `client` attribute which is populated from the `client_id` user session attribute when an instance is created (see the `init()` method annotated with `@PostConstruct`).

2. The `Users` role is marked as default and should be assigned to all client users. It restricts access to security screens and does not allow client admins to modify existing roles.

3. The `AppLifecycle` bean registers additional entity listener for the `User` entity. The listener ensures that default roles (including `Users`) are assigned to new users.

4. Due to the constraint for `sec$Role` entity assigned to the Clients group, client users see only roles with names starting with `client_`. That is they do not see `Administrators` and `Users` roles.

Based on CUBA Platform 6.6.1
