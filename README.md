mediabot
========

Some media organizer

Commandes Java Keytool
======================

Le Java Keytool est une clef et un outil de gestion de certificat. Cela permet aux utilisateurs de gérer leur propres paires de clef publique/privée et les certificats. Le Java Keytool range les clefs et certificats dans ce que l'ont appelle un keystore. Un Keytool keystore contient la clef privée et tout certificat nécessaire pour compléter une chaîne de confiance et établir la valeur de confiance du certificat primaire.

Chaque certificat dans un Keystore Java est associé avec un alias unique. Quand vous créez un Java Keystore, vous aurez tout d'abord à créer le fichier .jks qui initialement contient seulement la clef privée. Vous aurez alors à générer un CSR et avoir un certificat généré à partir de ce dernier. Alors vous importerez le certificat au keystore en incluant tout type de certificats racine (root).

Ci-dessous, nous avons listé les commandes keystore Java Keytool les plus communes et leur usage:

Les Commandes de Java Keytool pour la Création et l'Import
----------------------------------------------------------

Ces commandes vous permettent de générer un nouveau fichier keystore Java Keytool, créer un CSR, et importer des certificats. Tout type de certificats root ou intermédiaire auront besoin d'être importé avant d'importer le certificat primaire pour votre domaine.

* Générer un Keystore Java et une paire de clef
keytool -genkey -alias mydomain -keyalg RSA -keystore keystore.jks

* Générer un Certificate Signing Request (CSR) pour un Java Keystore existant
keytool -certreq -alias "mydomain" -keystore keystore.jks -file mydomain.csr

* Importer un Certificat CA racine ou intermédiaire à un Keystore Java existant
keytool -import -trustcacerts -alias root -file Thawte.crt -keystore keystore.jks

* Importer un certificat primaire signé à un keystore Java existant
keytool -import -trustcacerts -alias mydomain -file mydomain.crt -keystore keystore.jks

* Générer un keystore et certificat auto-signé
keytool -genkey -keyalg RSA -alias "selfsigned" -keystore keystore.jks -storepass "password" -validity 360

Les Commandes Java Keytool pour le Checking
-------------------------------------------

Si vous avez besoin de vérifier l'information au sein du certificat, ou Java Keystore, utilisez les commandes suivantes.

* Vérifier un certificat stand-alone
keytool -printcert -v -file mydomain.crt

* Vérifier quels certificats se trouvent dans un keystore Java
keytool -list -v -keystore keystore.jks

* Vérifier l'entrée d'un keystore particulier en utilisant un alias 
keytool -list -v -keystore keystore.jks -alias mydomain

D'autres Commandes Java Keytool
-------------------------------

* Supprimer un certificat d'un keystore Java Keytool
keytool -delete -alias "mydomain" -keystore keystore.jks

* Changer un mot de passe Java keystore
keytool -storepasswd -new new_storepass -keystore keystore.jks

* Exporter un certificat d'un keystore
keytool -export -alias mydomain -file mydomain.crt

* La liste des Certificats CA de Confiance
keytool -list -v -keystore $JAVA_HOME/jre/lib/security/cacerts

* Importer un Nouveau CA dans les Certificats de Confiance
keytool -import -trustcacerts -file /path/to/ca/ca.pem -alias CA_ALIAS -keystore $JAVA_HOME/jre/lib/security/cacerts
