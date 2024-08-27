# Order Service

De Order Service is een microservice die verantwoordelijk is voor het beheer van bestellingen binnen de webshop.

## Functionaliteiten

- **Bestelling plaatsen**
- **Bestellingen opvragen**
- **Bestellingsdetails opvragen via ID**

## API Spec

De API-specificatie is te bekijken via de Swagger UI. Start de applicatie en 
navigeer naar [http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html).

Voor het gebruik van de endpoints is authenticatie vereist (Bearer token). Je kunt een 
bearer token bemachtigen door een gebruiker te registreren via de User Service en daarna in te loggen.

## Implementeren microservice

De implementatie van deze microservice volgt dezelfde stappen als bij de Cart, Product, en User Services.

- **Kopiëren bestaande order functionaliteiten**
- **Applicatie configuratie**: deze applicatie draait op poort `8084`.
- **Database configuratie**: de database draait op poort `5436`.
- **Proxy/API-Gateway configuratie**

### Database configuratie

De Order Service maakt gebruik van een eigen database. Zorg ervoor dat deze database op een 
andere poort draait dan de databases van andere services.

1. **Database aanmaken:** Kopieer het `docker-compose.yml` bestand van de andere services en pas de poort aan naar **5436**.
2. **Configuratie:** Zorg ervoor dat de databaseconfiguratie in het `application.yml` bestand overeenkomt met de nieuwe database-instellingen.
3. **Docker:** Start Docker Desktop en voer het volgende commando uit in de root van het project:
    ```shell
    docker compose up -d
    ```
4. **Database schema's migreren:** Kopieer de Flyway-migratiebestanden vanuit de monolithische applicatie naar de `src/main/resources/db/migration` map van de Order Service.
5. **Schema's toepassen:** Start de applicatie om de schema's automatisch aan de nieuwe database toe te voegen.

### Inter-servicecommunicatie opzetten

Er is communicatie nodig met de User, Cart, en Product services. Dit gebeurt op dezelfde manier als bij de andere services.

#### Authenticatie

Authenticatie gebeurt op dezelfde manier als bij de Cart Service.
