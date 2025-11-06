# Microservice de Gestion des Garages Renault

Note on Lombok Usage !!

#######################

I chose not to use Lombok in this microservice because I encountered issues with getters, particularly getId(), not being properly recognized. I tried several solutions to fix it, including different configurations and plugin setups.

Eventually, I considered using JPA Buddy, but since I did not have the first version of the plugin, I decided to disable it.

As a result, this project uses standard Java getters and setters, along with constructors generated directly in the microservice classes. This ensures compatibility and avoids runtime issues with object mapping and persistence.

## 🎯 Use Case
Ce microservice a pour objectif de gérer les informations liées aux garages affiliés au réseau Renault.  
Il permet la centralisation, la consultation et la mise à jour des données concernant :

- Les garages du réseau Renault
- Les véhicules pris en charge
- Les accessoires disponibles

## 🏢 Contexte
Renault souhaite moderniser et structurer la gestion de son réseau en mettant en place une solution microservice pouvant être intégrée facilement avec d’autres services du SI existant.  
Ce microservice doit répondre aux exigences suivantes :

- Assurer une gestion fiable et à jour des garages et de leurs informations administratives.
- Permettre la gestion des véhicules associés à chaque garage.
- Offrir un catalogue des accessoires liés aux garages ou véhicules.
- Respecter des contraintes métiers spécifiques (règles d'affiliation, disponibilités, référentiels métiers, etc.).

## 📦 Fonctionnalités
- Création, consultation, mise à jour et suppression (CRUD) des garages.
- Gestion des véhicules associés à chaque garage.
- Gestion des accessoires (ajout, modification, retrait).
- Recherche filtrée par localisation, type de véhicule, ou type d’accessoire.

## 🧱 Architecture
Ce projet est basé sur une architecture microservice, facilitant :
- La scalabilité
- La maintenabilité
- L'intégration inter-services (API REST)

## 🔧 Technologies recommandées
- Java 21
- Spring Boot (Web, Data JPA, Validation)
- Base de données postgresql, Maven
