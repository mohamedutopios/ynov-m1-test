### Qu'est-ce que JMeter ?

**Apache JMeter** est un outil open-source utilisé pour les tests de performance et de charge des applications. Il est principalement conçu pour tester les applications web, mais il peut également être utilisé pour tester des API, des bases de données, des services web, des serveurs FTP et d'autres types de services. JMeter simule une lourde charge sur un serveur, un groupe de serveurs, un réseau ou un objet pour tester sa résistance et analyser les performances globales sous différents types de charges.

### Que permet-il ?

1. **Tests de Performance** :
   - Mesurer le temps de réponse des applications sous différentes charges.
   - Identifier les goulets d'étranglement et les problèmes de performance.

2. **Tests de Charge** :
   - Simuler plusieurs utilisateurs accédant à une application simultanément pour observer comment elle se comporte sous une charge lourde.
   - Déterminer la capacité maximale du serveur.

3. **Tests de Résistance** :
   - Tester la stabilité de l'application en la soumettant à une charge continue et soutenue pendant une longue période.
   - Identifier les fuites de mémoire et autres problèmes de stabilité.

4. **Tests de Scalabilité** :
   - Évaluer comment l'application se comporte en augmentant progressivement la charge.
   - Aider à planifier l'évolutivité de l'application en fonction des besoins futurs.

5. **Tests Fonctionnels** :
   - Bien que ce ne soit pas son principal objectif, JMeter peut également être utilisé pour les tests fonctionnels des applications web et des services.

### Mise en place de JMeter

#### Prérequis

1. **Java** : JMeter nécessite Java pour fonctionner. Assurez-vous d'avoir JDK ou JRE installé sur votre machine.
2. **Téléchargement** : Téléchargez la dernière version de JMeter depuis le site officiel d'Apache JMeter ([télécharger JMeter](https://jmeter.apache.org/download_jmeter.cgi)).

#### Installation

1. **Extraction** : Extrayez le fichier téléchargé dans un répertoire de votre choix.
2. **Configuration** : Aucune installation particulière n'est requise. Vous pouvez exécuter JMeter directement à partir des fichiers extraits.

#### Démarrage de JMeter

1. **GUI Mode** :
   - Naviguez vers le répertoire `bin` du répertoire JMeter extrait.
   - Exécutez le script `jmeter.bat` (Windows) ou `jmeter` (Unix/Linux/Mac) pour lancer l'interface graphique de JMeter.
   
   ```sh
   cd /path/to/jmeter/bin
   ./jmeter
   ```

2. **Non-GUI Mode** (pour exécuter des tests en ligne de commande, recommandé pour les tests de charge) :
   - Utilisez la commande suivante pour exécuter JMeter en mode non-GUI :
   
   ```sh
   jmeter -n -t /path/to/your/testplan.jmx -l /path/to/results.jtl -e -o /path/to/output-folder
   ```

   - `-n` : Non-GUI mode
   - `-t` : Chemin vers le fichier de plan de test JMeter (`.jmx` file)
   - `-l` : Chemin vers le fichier de résultats
   - `-e` : Générer le rapport à la fin du test
   - `-o` : Dossier de sortie pour le rapport généré

#### Création d'un Plan de Test

1. **Ouvrir JMeter** : Lancez JMeter en mode GUI.
2. **Ajouter un Thread Group** : 
   - Clic droit sur "Test Plan" > Ajouter > Threads (Users) > Thread Group.
   - Configurez le nombre d'utilisateurs (threads), la période de montée en charge (ramp-up), et le nombre de cycles (loop count).

3. **Ajouter des Samplers** :
   - Clic droit sur le Thread Group > Ajouter > Sampler.
   - Choisissez le type de requête que vous souhaitez envoyer (HTTP Request, JDBC Request, etc.).
   - Configurez les détails de la requête (URL, paramètres, etc.).

4. **Ajouter des Listeners** :
   - Clic droit sur le Thread Group > Ajouter > Listener.
   - Choisissez les types de rapports que vous souhaitez générer (Graph Results, View Results Tree, etc.).

#### Exemple de Test de Requête HTTP

1. **Thread Group Configuration** :
   - Number of Threads (users) : 10
   - Ramp-Up Period (seconds) : 5
   - Loop Count : 1

2. **HTTP Request** :
   - Server Name or IP : www.example.com
   - Path : /somepath

3. **Listener** :
   - Ajoutez un "View Results Tree" pour visualiser les résultats de chaque requête.

#### Exécuter le Plan de Test

1. **Exécution en mode GUI** :
   - Cliquez sur le bouton "Start" (icône de lecture verte) pour exécuter le test.
   - Visualisez les résultats dans les listeners ajoutés.

2. **Exécution en mode Non-GUI** :
   - Sauvegardez le plan de test (`.jmx` file).
   - Utilisez la commande en mode Non-GUI mentionnée ci-dessus pour exécuter le test.

