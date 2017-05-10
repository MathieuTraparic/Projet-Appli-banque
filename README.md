# Projet-Appli-banque

Fonctionnalités implémentés :
- afficher les comptes avec leur solde actuel
- gestion de multiples banques/agences/comptes
- CRUD sur la plupart des classes du model
- afficher les transactions de chaque compte
- afficher/editer des données dans un tableView
- afficher un graph de l'évolution d'un compte
- exporter les transaction d'un compte format csv
- générer un code IBAN avec sa clé
- générer un RIB au format PDF 
- gerer la connection/creation d'utilisateurs avec stockage de mot de passe hashé et salé
	-gérer la modification login/mdp
- vérifier la validité des inputs de l'utilisateur
- virements internes de comptes à comptes
- traitements par lambda expressions
- listeners, callback
- passage de données entre fenêtres (popup/view)
- javadoc
- gestion des intérets 
- gestion des alertes
- gestion des agios
- importer des données en CSV (format Banque postale)

Fonctionalités manquantes :

- gestion des transactions périodiques à finir
- filtrer les opérations affichés
- tests et commentaires à étoffer, utilisation de mockups
- utilisation d'interfaces

Design pattern à rajouter ?
 
- wrapper sur BankSelector et AccountSelector
- mediator au lieu d'un singleton VistaNavigator


	


#tutos
Tuto pour connecter la DB au projet Java:
http://www.objis.com/formation-java/tutoriel-java-acces-donnees-jdbc.html

http://www.javafxtutorials.com/

http://stackoverflow.com/questions/36463725/changing-scenes-in-javafx-nullpointerexception/36463793#36463793

Sample for creating a role based UI using JavaFX FXML 
https://gist.github.com/jewelsea/7229260

Allow a Tab key press in a TextArea to focus on the next field and an Enter key press in a TextArea to trigger a default button. 
https://gist.github.com/jewelsea/5624145

Sample for accessing a local database from JavaFX.
https://gist.github.com/jewelsea/4955598

Sample for accessing a local database from JavaFX using concurrent tasks for database operations so that the UI remains responsive. 
https://gist.github.com/jewelsea/4957967

Example JavaFX ChoiceBox control backed by Database IDs 
https://gist.github.com/jewelsea/1422104

JavaFX sample for an fxml based Login screen to main application transition with login session data transfer 
https://gist.github.com/jewelsea/4631319

JavaFX Popup example 
https://gist.github.com/jewelsea/1926196

tabPane
http://bekwam.blogspot.fr/2014/10/tabbed-navigation-in-java-fx.html

Stack Pane 
https://blogs.oracle.com/acaicedo/entry/managing_multiple_screens_in_javafx1

Combobox
http://stackoverflow.com/questions/13032257/combo-box-javafx-with-fxml

Storing passwords 
https://crackstation.net/hashing-security.htm
