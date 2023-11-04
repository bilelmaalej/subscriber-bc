# subscriber-bc
Projet de gestion des abonnée de canal plus

# My Canal+ Subscriber Management Application

Bienvenue dans l'application de gestion des abonnés Canal+ ! Cette application vous permet de gérer les abonnés Canal+ et d'appliquer des règles métier spécifiques.

## Configuration requise

Pour exécuter cette application, vous aurez besoin des éléments suivants :
- JDK 17
- Apache Maven

## Choix technique

- Java 17
- Apache Maven
- Base de donnée H2 en mémoire
- Architecture MVC
- JWT pour la sécurité
- Mapstruct pour le mappin g(entre DTO et entity)
- Swagger pour la documentation des services

- NB : Je joins deux collections postman (v 2 et V2.1) pour tester les endpoints 

## Lancer l'application

L'application utilise le framwork spring boot aec un serveur Tomcat intégrer 
Pour la lancer soit : mvn spring-boot:run
ou bien : lancer le fichier SubscriberBcApplication.java
L'application sera accessible à l'adresse http://localhost:8080.

## Endpoints REST

L'application expose les endpoints REST suivants pour gérer les abonnés Canal+ :

- POST api/v1/subscribers : Crée un abonné.
- GET api/v1/subscribers/search : Récupère un abonné par n'importe quel critère de recherche.
- PATCH /subscribers/{id}/cancel : Désactive un abonné.
- PUT /subscribers/{id} : Met à jour les données d'un abonné.

J'ai aussi ajouté un endpointt : 
- GET api/v1/subscribers/search/findAll : Retourner tout les abonnés

## Règles Métier

J'ai mis en place des validateur pour vérifier la cohérence des données (les mails, les numéros de téléphones...)

## Les exceptions

Pas mal d'exception fonctionnelles sont mis en place pour vérifier le traitement

## Tests Unitaires

Des tests unitaires sont inclus pour valider les règles métier et les fonctionnalités de l'application. 

## Swagger Documentation

La documentation de l'API est disponible via Swagger à l'adresse http://localhost:8080/swagger-ui.html. 
Vous y trouverez des informations détaillées sur les endpoints, les paramètres et les réponses.

Pour avoir un code claire et lisible, j'ai utulisé :
- Des commentaires
- Des fichiers de logs (logback)
- De la JavaDoc

## To do :

- Compléter la partie JWT (commentée pour le momont)
- Ajouter des role (admin, user,...)
- Compléter les tests
