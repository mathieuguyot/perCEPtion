#Framework perCEPtion
Bienvenu sur le dépot officiel du framework perCEPtion !
```diff
+ Attention, le framework est en phase de développement et n'est pas exploitable pour l'instant !
+ Readme en travaux !
```
***
PerCEPTion est un framework de monitoring basé sur le traitement des événements complexes (Complex Event Processing - CEP) permettant de mettre en place une surveillance avancée d'une architecture matérielle et logicielle (Cloud par exemple). En outre, il permet de détecter des signes d'incohérence au sein d'un ensemble de composants d'une architecture. Si des incohérences sont détectées par le framework, celui-ci fournit un ensemble d'informations permettant d'aider à la reconfiguration des composants affectés.
***
Ce Framework à été redesigné à partir d'un framework développé par Simon Dupont et des chercheurs et étudiants de l'Institut Mines-Télécom Atlantique. Apache Flink est utilisé dans la nouvelle version du framework.
Il suit assez fidèlement les concepts de la thèse de Simon Dupont : ["Gestion autonomique de l'élasticité multi-couche des applications dans le Cloud : vers une utilisation efficiente des ressources et des services du Cloud"](https://tel.archives-ouvertes.fr/tel-01344377/)

>La dernière version de framework a été conçue par 4 étudiants de l'Institut Mines-Télécom Atlantique :
>- Chloé GUILBAUD
>- Kendall FOREST
>- Mathieu GUYOT
>- Léo PARIS 
<p align="center">
![alt text](https://www.imt-atlantique.fr/sites/default/files/logo_mt_0_0.png "Logo Title Text 1")

##Table des matières

[TOC]

##Fonctionnement général

Voici les différents types d’événements qui sont utilisés dans perCEPtion
Type d'évènement | Description
-------- | ---
Primitif event(PE) | Un événement primitif contient une information sur un seul composant de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés primitive events generator (PEG).
Simple event (SE) | Un événement simple contient une ou plusieurs informations sur un seul composant de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés simple events generator (SEG).
Complex event (CE) | Un événement complexe contient une ou plusieurs informations sur un ou plusieurs composants de l'architecture qui est surveillée. Il est obtenu grâce à des composants appelés complex events generator (CEG).

##Utilisation du Framework

Nous avons fait en sorte que l'utilisation du framework soit le plus simple possible !
Les sections ci-dessous détaillent comment utiliser perCEPtion.

### Créer ses événements
Créer ses propres événements est simple, il vous suffit de :
>- Faire hériter votre classe de PrimitiveEvent pour créer votre propre événement primitif.
>- Faire hériter votre classe de SimpleEvent pour créer votre propre événement simple.
>- Faire hériter votre classe de ComplexEvent pour créer votre propre événement complexe.
>- Faire hériter votre classe de Symptom pour créer votre propre symptôme. 
> La structure de ses classes est assez simple :

### Créer ses primitive events generator

### Créer ses simple events generator

# En travaux !
