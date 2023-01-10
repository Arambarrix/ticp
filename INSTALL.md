Pour utiliser cette application, il faudra vous munir de :
- Un serveur MySQL
- Un serveur Node.js
- Une version de Java d'au moins 1.8 et Maven

L'application a deux parties, un frontend et un backend.

Dossier Backend
- Il comporte un fichier application.properties qui génère la création et les requêtes de la base de données. Pour accéder à la base de données, il faudra modifier le nom d'utilisateur et le mot de passe, selon ce que votre configuration MySQL comporte.
- Il comporte une classe isolée TicpbackendApplication.java qui permet de lancer le backend.


Dossier Frontend
- Pour lancer le frontend, on fait "npm install" puis "npm run dev" dans le dossier Frontend.
