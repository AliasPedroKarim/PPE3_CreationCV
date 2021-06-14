<p align="center">  
    <img src="https://image.flaticon.com/icons/svg/942/942782.svg" width="100"></img>  
</p>  

---
  
### Projet Personnel Encadré n°3 - CV Creator  
<p>  
	<img src="https://raster.shields.io/badge/version-0.2-brightgreen"></img> 
	<img src="https://img.shields.io/github/stars/AliasPedroKarim/PPE3_CreationCV"></img> 
	<img src="https://img.shields.io/github/repo-size/AliasPedroKarim/PPE3_CreationCV"></img> 
	<img src="https://img.shields.io/github/issues/AliasPedroKarim/PPE3_CreationCV"></img> 
	<img src="http://hits.dwyl.io/AliasPedroKarim/AliasPedroKarim/PPE3_CreationCV.svg"></img> 
</p>

---
  
#### Description  
  
Ceci est un Project Personnel Encadrer dans le cadre du lycée Jean Lurçat, pour un BTS SIO SLAM 2éme. Dans le but de créer un logiciel de gestion de Curriculum Vitae, le PPE n°3 de 2e année BTS a été initié. Le logiciel doit principalement être réalisé en Java.  
  
#### Configuration de base  
Compétence minimal :  
- Savoir utiliser **PhpMyAdmin**  
- Manipulation de fichier JSON  
  
##### Base de donnée (MySQL + PhpMyAdmin)  
Vous avez à votre disposition un fichier base de donnée MySQL `db.sql` dans le dossier `.util`, donc il vous suffit tout simplement d'importer le fichier SQL sur l'interface PhpMyAdmin qui va interagir avec la base de données, pour vous générer les tables qu'il faut sur une base de données que vous aurez préalablement créé.  
  
En ce qui concerne la configuration au niveau programmation, il va falloir faire de petites modifications pour permettre au logiciel de pouvoir interagir avec votre base de données locales  (ou externe) donc il faut aller sur le fichier `src\main\java\fr\karim\connexion\DaoSIOExample.java`, il faudra refactorer le fichier `DaoSIOExample.java` en fichier `DaoSIO.java` pour qu'il soit prise en compte par le logiciel.    
  
![](https://i.imgur.com/owrb6ec.png)  
  
**NB :** En installant la base de données, vous aurez accès à deux compte un administrateur (user : `admin`, pass : `000000`) et l'autre utilisateur (user : `user`, pass: `000000`)  
  
##### Configuration des connexions API (Google + Facebook)  
  
En ce qui concerne la configuration des connexions API (Google et Facebook), si vous êtes le vérificateur ou le prof du projet, les informations de connexion API vous seront donner dans le document récapitulatif des projets.  
  
Sinon, si vous êtes un utilisateur extérieur à cet examen, vous pouvez toujours vous lancer sur la création des clients ID et des clients secrets de [Google](https://developers.google.com/) et de [Facebook](https://developers.facebook.com/) où pouvoir utiliser ce logiciel.  
  
Une fois muni de vos clients ID et de vos clients secrets, pour chacun des fichiers de configurations dans `src\main\resources` donc `client_secrets.facebook.example.json` et `client_secrets.facebook.example.json`, il faudra renommer les fichiers en enlevant `exemple`, mais surtout il faudra pour chaque fichier remplir la configuration qu'il faut comme ceci :      
![](https://i.imgur.com/FxMQDlj.png)  
  
  
#### Fonctionnalité du Logiciel  
  
- **une interface de connexion et inscription**
    - Inscription avec les informations de l'API Facebook ou Google  
    - Photo de profile  
- **Permet la création de multiples Curriculum Vitae**  
    - On peut spécifier de multiples données notamment **l'expérience professionnelle**,  **la formation**
	- Le titre, la description du CV, ...
- **Vous pouvez importer exporter vos données**   
	- en JSON  
	- en XML  
	- en CSV  
- **Vous avez un accès à une espace administration si votre compte est administrateur**
    - Création, Modification ou Suppression d'un utilisateur  
- **Vous avez un accès un espace utilisateur où vous pouvez voir tous les informations actuelles ou même les modifier**  
- **vous pouvez aussi imprimer en document PDF un Curriculum Vitae**  
  
#### Installation et/ou récupération  
  
**Récupérer le code :**
+ <u>__1er Méthode :__</u> Le code source est téléchargeable avec ce lien [GitHub](https://github.com/AliasPedroKarim/PPE3_CreationCV/archive/master.zip) _(Lien de la branche master)_
    + Comme c'est un fichier zip, veuillez le dé-compilé. 
	+ Sur l'IDE Netbeans ou autre, créer un nouveau projet Maven  
	+ Une fois créer, ouvrer aussi le projet composant sous Netbeans  
+ <u>__2eme Méthode :__</u> Sous Netbeans, un onglet `Team` est disponible et on peut clone les projets GitHub grâce au lien `.git` disponible en ⬆ de la page `https://github.com/AliasPedroKarim/PPE3_CreationCV.git` :    
     
     ![](https://i.imgur.com/aoDpRMu.png)  
     ![](https://i.imgur.com/wiwWBJ8.png)  
  
#### ArgoUML  (Diagramme des classes)
Voici le lien pour [Télécharger](http://argouml-downloads.tigris.org/nonav/argouml-0.34/ArgoUML-0.34-setup.exe) ArgoUML pour pouvoir interagir avec le diagramme des classes du projet. un fichier ArgoUML est présent à la racine du projet c'est un fichier `.zargo`, il suffira de l'ouvrir avec ArgoUML.
  
#### Langage utiliser  
- Java  
- Javascript  
  
#### Dépendance module  
_pour connaître la liste des dépendances présence sur le logiciel, veuillez vous référer sur le fichier `pom.xml` dans la section dépendance_  

#### Plus de documentation
[Plus de Documentation...](#)

#### Crédit  
Lycée Jean Lurcat
