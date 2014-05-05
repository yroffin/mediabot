mediabot
========

Some media organizer

Commandes Java Keytool
======================

Le Java Keytool est une clef et un outil de gestion de certificat. Cela permet aux utilisateurs de g�rer leur propres paires de clef publique/priv�e et les certificats. Le Java Keytool range les clefs et certificats dans ce que l'ont appelle un keystore. Un Keytool keystore contient la clef priv�e et tout certificat n�cessaire pour compl�ter une cha�ne de confiance et �tablir la valeur de confiance du certificat primaire.

Chaque certificat dans un Keystore Java est associ� avec un alias unique. Quand vous cr�ez un Java Keystore, vous aurez tout d'abord � cr�er le fichier .jks qui initialement contient seulement la clef priv�e. Vous aurez alors � g�n�rer un CSR et avoir un certificat g�n�r� � partir de ce dernier. Alors vous importerez le certificat au keystore en incluant tout type de certificats racine (root).

Ci-dessous, nous avons list� les commandes keystore Java Keytool les plus communes et leur usage:

Les Commandes de Java Keytool pour la Cr�ation et l'Import
----------------------------------------------------------

Ces commandes vous permettent de g�n�rer un nouveau fichier keystore Java Keytool, cr�er un CSR, et importer des certificats. Tout type de certificats root ou interm�diaire auront besoin d'�tre import� avant d'importer le certificat primaire pour votre domaine.

* G�n�rer un Keystore Java et une paire de clef
keytool -genkey -alias mydomain -keyalg RSA -keystore keystore.jks

* G�n�rer un Certificate Signing Request (CSR) pour un Java Keystore existant
keytool -certreq -alias "mydomain" -keystore keystore.jks -file mydomain.csr

* Importer un Certificat CA racine ou interm�diaire � un Keystore Java existant
keytool -import -trustcacerts -alias root -file Thawte.crt -keystore keystore.jks

* Importer un certificat primaire sign� � un keystore Java existant
keytool -import -trustcacerts -alias mydomain -file mydomain.crt -keystore keystore.jks

* G�n�rer un keystore et certificat auto-sign�
keytool -genkey -keyalg RSA -alias "selfsigned" -keystore keystore.jks -storepass "password" -validity 360

Les Commandes Java Keytool pour le Checking
-------------------------------------------

Si vous avez besoin de v�rifier l'information au sein du certificat, ou Java Keystore, utilisez les commandes suivantes.

* V�rifier un certificat stand-alone
keytool -printcert -v -file mydomain.crt

* V�rifier quels certificats se trouvent dans un keystore Java
keytool -list -v -keystore keystore.jks

* V�rifier l'entr�e d'un keystore particulier en utilisant un alias 
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
