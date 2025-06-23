### Explication des Données du Graph Results

1. **Average (Moyenne)** :
   - **Description** : La moyenne des temps de réponse pour toutes les requêtes durant le test.
   - **Interprétation** : Indique le temps moyen qu'il a fallu pour que le serveur réponde à une requête. Un temps moyen élevé peut indiquer des problèmes de performance.

2. **Median (Médiane)** :
   - **Description** : Le temps de réponse médian pour les requêtes. La médiane est la valeur au milieu de votre ensemble de données, c'est-à-dire que 50 % des requêtes ont un temps de réponse inférieur à cette valeur et 50 % ont un temps de réponse supérieur.
   - **Interprétation** : Utile pour comprendre la distribution des temps de réponse et identifier les anomalies. Si la médiane est significativement inférieure à la moyenne, cela peut indiquer la présence de valeurs aberrantes élevées (outliers).

3. **90% Line** :
   - **Description** : Le temps de réponse en dessous duquel 90 % des requêtes sont traitées.
   - **Interprétation** : Un bon indicateur de performance pour la majorité des utilisateurs. Si cette valeur est élevée, cela signifie que 10 % des utilisateurs ont des temps de réponse plus longs, ce qui pourrait nécessiter une enquête.

4. **Min (Minimum)** :
   - **Description** : Le temps de réponse le plus court enregistré pendant le test.
   - **Interprétation** : Indique le meilleur cas de performance du serveur.

5. **Max (Maximum)** :
   - **Description** : Le temps de réponse le plus long enregistré pendant le test.
   - **Interprétation** : Indique le pire cas de performance du serveur. Un temps maximum élevé peut indiquer des problèmes sporadiques ou des surcharges.

6. **Error % (Pourcentage d'erreurs)** :
   - **Description** : Le pourcentage de requêtes qui ont échoué par rapport au nombre total de requêtes.
   - **Interprétation** : Un pourcentage élevé d'erreurs peut indiquer des problèmes avec l'application, des erreurs de configuration de test, ou des problèmes réseau.

7. **Throughput (Débit)** :
   - **Description** : Le nombre de requêtes traitées par minute ou par seconde.
   - **Interprétation** : Mesure la capacité du serveur à traiter les requêtes. Un débit élevé avec des temps de réponse bas est généralement le signe d'une bonne performance.

8. **Received KB/sec** :
   - **Description** : Le taux de réception de données par seconde en kilooctets.
   - **Interprétation** : Indique la quantité de données reçues du serveur par unité de temps.

9. **Sent KB/sec** :
   - **Description** : Le taux d'envoi de données par seconde en kilooctets.
   - **Interprétation** : Indique la quantité de données envoyées au serveur par unité de temps.

### Comment Utiliser le Graph Results

1. **Ajouter le Listener Graph Results** :
   - Clic droit sur le Groupe de Threads -> Add -> Listener -> Graph Results.

2. **Exécuter le Test** :
   - Cliquez sur le bouton de démarrage (icône verte) pour lancer le test de performance.

3. **Observer les Graphiques** :
   - Pendant que le test s'exécute, le Graph Results affiche les courbes représentant les différentes métriques (Average, Median, 90% Line, Min, Max, etc.).

4. **Analyser les Données** :
   - Identifiez les pics et les tendances dans les courbes pour comprendre le comportement de votre application sous charge.
   - Utilisez les différentes métriques pour diagnostiquer les problèmes de performance et identifier les points faibles à améliorer.

### Exemple de Visualisation

Voici un exemple d'analyse basée sur un Graph Results :

- **Si la courbe de l'Average (Moyenne) montre une tendance à augmenter sous une charge croissante**, cela pourrait indiquer que votre serveur a du mal à gérer un grand nombre de requêtes simultanées.
- **Une valeur élevée de la 90% Line par rapport à l'Average** peut indiquer que quelques requêtes prennent beaucoup plus de temps que la majorité, signalant des problèmes potentiels dans certaines parties de l'application ou des surcharges momentanées.
- **Un pourcentage d'erreur élevé (Error %)** nécessite une attention immédiate pour comprendre la cause des échecs (par exemple, erreurs 500 du serveur, erreurs de validation, etc.).
- **Le débit (Throughput)** vous donne une idée de la capacité du serveur à traiter les requêtes. Si le débit reste constant mais que les temps de réponse augmentent, cela peut indiquer une saturation du serveur.

