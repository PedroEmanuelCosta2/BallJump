Résumé
======

Le projet Ball Jump a été réalisé dans le cadre du cours de Développement mobile, un cours de 3ème année de Bachelor au sein de la Haute-École Arc - Ingénierie, section Développement Logiciel et Multimédia.
Ce document décrit le déroulement du projet et présente les fonctionnalités implémentées.
Ball Jump est un jeu mobile qui se déroulere sur le principe du jeu "Doodle Jump".

Principe du jeu
===============

Le jeu se déroule de la manière suivante. Le joueur doit diriger un personnage en inclinant le téléphone de gauche à droite.
Le terrain de jeu est composé d’une multitude de plateformes qui font rebondir le personnage lorsqu'il atterit dessus.

Le but du jeu est d’arriver le plus haut possible.
Les plateformes se génèrent au fur et mesure que le personnage monte.
Des items qui permettront de débloquer des bonus se trouveront également dans le décore.

Structure
=========

MenuActivity
^^^^^^^^^^^^

Première activitée à être lancé lors de l'ouverture de l'application. Elle permet juste d'afficher le menu principal qui contient les boutons suivant.
* PLAY : lancer le jeu
* STATISTICS : affiche des statistiques sur les scores
* SHARE	: partage de scores en bluetooth
* CREDIT : crédit du jeu

Ensuite en fonction des boutons pressés l'activité va lancer les activités correspondante.

Statistics
^^^^^^^^^^

Statistics va lire les données d'un fichier .txt afin d'en calculer une moyenne, d'en afficher le meilleur score et le score des cinq dernières parties. Les score seront affichés à l'aide d'une expandable list view, cette dernière est légèrement plus complexe qu'une liste car son adapteur est plutôt difficile d'utilisation. C'est pour cela, qu'il a fallu en créé un qui étend la classe BaseExpandableListAdapter pour en redéfinir ses fonctions en utilisant une liste (correspondants aux éléments sous un titre) et un dictionnaire qui contiendra comme clé le titre et comme valeur les listes.

SensorAccelerationActivity
^^^^^^^^^^^^^^^^^^^^^^^^^^

Cette classe s'occupe d'instancier le panel ainsi que de gèrer les capteurs. Elle utilise l'accéléromètre et le magnétomètre pour pouvoir trouver l'angle de l'appareil. Pour cela la classe implémente SensorEventListener et doit surcharger la fonction onSensorChanged() qui est appelé a chaque changement de valeur des capteurs. Dans cette fonction on récupère la valeur des deux capteurs et on utilise la classe SensorManager pour calculer l'angle de la manière suivante. On passe les résultats des capteurs dans la fonction getRotationMatrix() de la classe SensorManager qui prends comme paramètre 4 tableau, 2 qui viennent des capteurs et 2 tableau dans lesquels seront stocké les résultat. Un de ces tableau correspond a une matrice de rotation qui est passée dans une autre fonction de SensorManager qui se nomme getOrientation() qui elle va retourné un tableau d'angle selon tous les axes. Donc on passe l'angle qui nons intéresse au panel pour qu'il fasse bouger le personnage du jeu.

GamePanel
^^^^^^^^^

Cette classe est la classe principale de notre projet, c'est dans cette dernière que toutes les fonctions essentielles sont appelées et utilisées pour le bon fonctionnement du jeu. Parmis ces fonctions se trouve la fonction update qui se chargera de mettre à jour les mouvements de tous les objets qui sont affichés à l'aide de la fonction draw. C'est dans le GamePanel que les appels de la logique du jeu se trouvent, par exemple lorsque le joueur meurt on appelle la fonction de SensorAccelerationActivity qui permet de déplacer le joueur dans un menu de GameOver.

Plateform
^^^^^^^^^

Afin de créer ces blocs permettant au joueur de rebondir, nous avons mis en place une classe qui créera des rectangles servant de plateformes. Dans cette classe, nous trouverons les fonctions qui permettent de les déplacer, les redessiner et d'instaurer une logique permettant de rendre une plateforme sur 40 "destructible". Destructible signifie qu'une fois que le joueur rebondit sur la plateforme, cette dernière se détruit. 

Player
^^^^^^

Dans la classe du joueur, on implémente les fonctions de ses mouvements, de ses collision avec une plateforme et de son rebond continuel. 

GameOver
^^^^^^^^

L'activité Game Over intervient lorsque le joueur tombe mais n'arrive pas à rebondir sur une plateforme. Cette activité permet de soit directement jouer une nouvelle partie, ou alors de retourner au menu afin d'observer les scores par exemple. 

ShareActivity
^^^^^^^^^^^^^

Permet de choisir entre paratger son score ou reçevoir celui d'un autre joueur. Donc pour que 2 joueurs se partagent les scores un joueur doit presser le bouton GET SCORE et l'autre doit presser SEND SCORE.

ServerActivity
^^^^^^^^^^^^^^

Créer le serveur et affiche une liste d'adresses ip. Quand un joueur se connecte et partage son score il est également affiché.

ClientActivity
^^^^^^^^^^^^^^

Possède une zone de texte éditable pour entrer l'adresse IP du serveur et un texte qui affiche si le client est connecté ou pas. Cette activité possède également deux boutons, un qui permet de se connecter au serveur et un autre qui permet d'envoyer le score une fois connecté au serveur. 



Fonctionnalités
===============

Cette section développe les fonctionnalités du téléphone qui sont utilisés par l'application.

Capteurs
^^^^^^^^

Dans le cadre de ce projet, les capteur utilisé sont l'accéléromètre et le magnétomètre.

Selon Wikipédia_,

    *Un accéléromètre est un capteur qui, fixé à un mobile ou tout autre objet, permet de mesurer l'accélération linéaire de ce dernier. On parle d'accéléromètre même lorsqu'il s'agit en fait de 3 accéléromètres qui calculent les accélérations linéaires selon 3 axes orthogonaux.*

Il s'agit plus simplement d'un capteur qui permet de détecter les mouvements du téléphone.
Dans ce projet, il est utilisé pour détecter les changements d'orientation afin de déplacer le joueur sur l'axe horizontal du jeu.

Le magnétomètre lui est un capteur qui détecte les changements dans le champ magnétiques avoisinant.

Dans une utilisation combinée, ces deux capteurs permettent de détecter l'inclinaison du téléphone et donc de déplacer le joueur.

Plateformes
^^^^^^^^^^^

La génération aléatoire des plateformes se fait dans la fonction plateformsGeneration se trouvant dans le GamePanel. On va tester si le joueur atteint la moitié de l'écran afin de générer un ensemble de plateformes sur un delta que le joueur a pu grimper. De cette façon on a l'impression que le joueur monte de façon fluide.  

Rebonds
^^^^^^^

Pour les rebonds c'est assez simple, quand une colision est détecté entre le joueur et une plateforme on assigne une vitesse au joueur positive. Cela a pour éffet de faire monter le personnage. A chaque déplacement du joueur la vitesse est décrémentée. Donc il va monter de moins en moins vite et ensuite la vitesse va devenir négative par conséquent le joueur va redéscendre jusqu'à atteindre une vitesse maximum. Ensuite si il entre a nouveu en collision il va remonter à nouveau.

Le joueur monte seulement jusqu'à le moitié de l'écran et ensuite se sont les plateformes qui descandent ce qui donne l'impression que le joueur monte. Cette manière de faire évite que le joueur sorte de l'écran vers le haut si il saute plusieurs fois de suite.

Persistance
^^^^^^^^^^^

Dans le but de pouvoir créer des statistiques, les scores sont sauvegardés dans un fichier texte. Afin de simplifier le stockage et n'ayant qu'un champ à sauvegarder, cette solution est avantageuse comparée à une utilisation d'une base de données.

Partage des scores
^^^^^^^^^^^^^^^^^^

Au départ nous avions décidé de faire le partage des scores par bluetooth mais nous ne somme pas pavenu à le faire fonctionner. Donc nous avons décideé de faire cela avec des sockets. Pour cela quand on va sur le bouton SHARE une page s'affiche avec deux boutons, un pour recevoir le score d'un autre joueur (le serveur) et l'autre pour envoyer son score (le client).  

Serveur:
Cela va afficher une liste d'adresses IPs car nous ne somme pas parvenu a déterminer laquelle était la bonne alors il faut que le client essaye de se connecter avec les adresses listées jusqu'à qu'il puisse se connecter. Ensuite le serveur va afficher la liste des scores de l'autre joueur.

Client:
Une zone de texte permet d'entrer l'adresse IP du serveur et un bouton CONNECT permet de se connecter au serveur. Un texte va afficher si la connection a fonctionnée ou non. Un deuxième bouton permet de d'envoyer le score au serveur correspondant. 

Problèmes rencontrés
====================

Cette section détaille les problèmes rencontrés, qu'il s'agisse d'un point de vue matériel ou de développement.

Liés aux appareils
^^^^^^^^^^^^^^^^^^

Capteurs
""""""""

Tous les téléphones ne disposant pas des mêmes capteurs et l'emulateur ne permettant pas de simuler des orientations, il a été difficile de pouvoir tester l'application. En effet, les tablettes prêtées par l'école ne disposent pas d'accéléromètre.

Ce problème a induit un codage à "l'aveugle" pour les étudiants ne disposant pas d'autres appareils Androïd. De plus, il n'a été possible de réaliser au préalable les tests uniquement sur un téléphone.

Puissance de calcul
"""""""""""""""""""

Tous les appareils ne disposant pas du même processeur et de la même quantité de RAM_, le jeu ne se déroulait pas à la même vitesses sur tous les périphériques.

Il a été nécessaire de trouver un moyen de ralentir les appareils trop rapides afin que l'expérience utilisateur soit toujours autant agréable.
Les appareils plus lents quant à eux ne sont pas ralentis et affichent le jeu au maximum de leur capacités.

Il est donc possible que le jeu se déroule plus lentement sur certains appareils, mais jamais "trop" vite.

Liés au développement
^^^^^^^^^^^^^^^^^^^^^

Collisions pas détectées
""""""""""""""""""""""""

Il y avait un problème quand le joueur retombait à la vitesse maximale car le joueur pouvait passé de dessus a en dessous d'une plateforme en une frame. Donc quand on vérifie les collisions il n'y en a pas. Pour régler le problème nous avons dû augmenter l'épasseur des plateformes virtuellement. C'est a dire que visuellement elles ne chagent pas mais dans la détection des collisions elles sont plus éapaisse. De cette manière il n'est plus possible de traveré une plateforme en une seul frame.

Panel qui ne se redéssine pas
"""""""""""""""""""""""""""""

Le problème était que lorsqu'on pressait sur PLAY dans le menu principal une page blanche s'affichait parfois pendant plusieurs dizaine de secondes. Après quelques recherches on a trouvé que le problème venait du fait qu'on lockait le canvas pour pouvoir déssiner dessus mais on unlockais pas au bon endroit qui avait pour effet de ne pas redessiner les modifications.

Après cette modification on a remarqué que l'affichage mettait un petit temps de chargement donc pour règler le problème le jeu ne démarre pas tout pendant que le joueur n'a pas touché l'écran comme cela le joueur démarre quand il est prêt-

Bugs connus
===========

Auto-critique
=============

Conclusion
==========


.. Bibliographie

.. _Wikipédia: https://fr.wikipedia.org/wiki/Accéléromètre
.. _RAM: https://fr.wikipedia.org/wiki/Mémoire_vive
