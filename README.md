# Superbowl Website (Back-end)

## Présentation

Ce projet (fictif) est la partie Back-end de l'application Superbowl (projet Stania).

Cette application est destinée à pouvoir visualiser les matches du Superbowl et à permettre aux utilisateur de parier sur des matches à venir.

Il fait appel à une une architecture N-Tiers classique avec :
- 1 application web front
- 1 application back-end (celle-ci) permettant un accès à :
- 1 système de gestion de base de données (MariaDB).

Le code source de la partie back-end du projet se trouve à ici :
https://github.com/jeromepierrot/superbowlfront.git

La base de donnée est hébergée sur un serveur séparé des applications.

2 autres applications sont en cours de développement :
- 1 version bureautique (Desktop) destiné aux commentateurs des matches.
- 1 version mobile (Android/iOS) pour que les utilisateurs puissent visualiser leur paris effectués sur le site web.

_(liens à venir)_

### Technologies utilisées
- JAVA 17*
- Spring Boot Starter Web 3.0.7 (incl RESTful API + Tomcat server)
- Spring Security
- Spring Data JPA (incl. Hibernate ORM) + MariaDB drivers
- Spring Validation (Hibernate Validator)
- Spring Actuator (Hibernate Validator)
- Spring Web MVC et Thymeleaf (template engine générant l'interface graphique pour la partie administration uniquement)
- DevTools
- Lombok

Test uniqtaire et fonctionnels :
- JUnit
- Mockito

## Installation :

Pour lancer l'application back-end dans un environnement local de dévelopement, il faut :

- Avoir le JDK Java version 17* installé sur la machine (OpenJDK ou équivalent : cf. https://adoptium.net/ pour obtenir un OpenJDK LTS)
- Maven pour la gestion de projet et des dépendances (cf. : https://maven.apache.org/install.html)
- Un système de gestion de Base de données relationnelles SQL (MariaDB ici)est requis (en server local ou hébergé).

*(La version 17 du JDK est nécessaire pour Spring Boot 3.0.x et Spring Security)

### Installation des dépendances :

Dans le terminal, en étant à l'intérieur du dossier du projet lancer la commande suivante :

`mvn clean install`
ou
`./mvnw clean install`

Une nouveau dossier 'target' incluant les dépendances sera créé (dossier à exclure des dépot Git).

## Lancement du projet :

Toujours depuis le terminal, entrer la commande suivante :
`mvn spring-boot:run`
ou
`./mvnw spring-boot:run`

Ouvrez ensuite une page d'un navigateur web puis entrer l'url 'http://localhost:8080/swagger-ui.html' afin d'afficher la
Notez que l'url http://localhost:8080' renvoi sur une page d'erreur : c'est normal puisqu'on n'est pas censé se connecter à la racine du serveur.

## Paramétrage de la base de données :

- Aller dans le dossier :
**src/main/resources**
- puis ouvrir le fichier :
**application.properties**
- vous pouvez configurer le port d'entrée du serveur Tomcat (par défaut : 8080)
- mais surtout les accès à la base de données de la section :

`#DATABASE MARIADB`

`spring.datasource.driver-class-name=org.mariadb.jdbc.Driver`
`spring.datasource.url=jdbc:mariadb://your_database_url/your_database_name`
`spring.datasource.username=use_your_username`
`spring.datasource.password=use_your_password`