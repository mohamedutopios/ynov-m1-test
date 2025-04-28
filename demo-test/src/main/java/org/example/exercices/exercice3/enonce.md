Pour cet exercice :


Vous allez devoir effectuer les opérations suivantes :


1. Créer une application simple (un model, un service et une exception) sur des transactions bancaires.

2.  Une transaction bancaire est une opération simple :

- On débite un compte d'une certaine somme et on credite un autre compte de ce même montant.
- Si dans les paramètres de la transaction, on n'a pas les comptes (null), on lèvera une exception de type IllegalArgumentException.
- Si le montant de la transaction est null ou négatif, on lèvera le même type d'exception.
- Si le montant de la transaction est supérieur au montant présent dans le compte à debiter : On lèvera une exception de personnalisé : NotEnoughMoneyException de type RunTimeException.
- Si la transation se déroulé correctement, on retournera un boolean.

3. Vous allez tester cette application :
- Il faudra ecrire 10 tests qui devrait couvrir les cas de figures enoncées précédemment.
- L'un de vos tests devra utilisé un assertAll()
- L'un de vos tests devra être un test imbiqué.
- Vous assurez qu'une transaction ne dépasse pas 1 seconde.
- Utiliser un test parametré sur les valeurs (100,200,50 et -10) concernant le montant du transfert.
- Tous les tests doivent réussir.