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

Fonctionnalités
===============

Cette section développe les fonctionnalités du téléphone qui sont utilisés par l'application.

Capteurs
^^^^^^^^

Dans le cadre de ce projet, le capteur utilisé est l'accéléromètre.

Selon Wikipédia_,

    *Un accéléromètre est un capteur qui, fixé à un mobile ou tout autre objet, permet de mesurer l'accélération linéaire de ce dernier. On parle d'accéléromètre même lorsqu'il s'agit en fait de 3 accéléromètres qui calculent les accélérations linéaires selon 3 axes orthogonaux.*

Il s'agit plus simplement d'un capteur qui permet de détecter les mouvements du téléphone.
Dans ce projet, il est utilisé pour détecter les changements d'orientation afin de déplacer le joueur sur l'axe horizontal du jeu.

Persistance
^^^^^^^^^^^

Les meilleurs scores du joueurs sont sauvegardés.

Bugs connus
===========


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


Auto-critique
=============


Conclusion
==========


.. Bibliographi

.. _Wikipédia: https://fr.wikipedia.org/wiki/Accéléromètre
.. _RAM: https://fr.wikipedia.org/wiki/Mémoire_vive
