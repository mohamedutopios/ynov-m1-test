Selenium en Java offre une multitude de méthodes et d'outils pour interagir avec les éléments web et contrôler le navigateur. Voici une liste des principaux outils et méthodes que vous utiliserez fréquemment avec Selenium WebDriver en Java :

### Outils Principaux

1. **WebDriver** : Interface principale pour contrôler le navigateur.
2. **WebElement** : Représente un élément HTML sur la page web.
3. **By** : Utilisé pour localiser des éléments sur la page web.

### Principales Méthodes de WebDriver

- **`get(String url)`** : Ouvre la page web à l'URL spécifiée.
- **`getTitle()`** : Retourne le titre de la page actuelle.
- **`getCurrentUrl()`** : Retourne l'URL de la page actuelle.
- **`findElement(By by)`** : Trouve le premier élément correspondant au sélecteur spécifié.
- **`findElements(By by)`** : Trouve tous les éléments correspondant au sélecteur spécifié.
- **`manage()`** : Retourne une interface `Options` pour gérer les paramètres du navigateur.
- **`navigate()`** : Retourne une interface `Navigation` pour contrôler la navigation du navigateur.
- **`quit()`** : Ferme toutes les fenêtres et met fin à la session WebDriver.
- **`close()`** : Ferme la fenêtre actuelle.

### Principales Méthodes de WebElement

- **`sendKeys(CharSequence... keysToSend)`** : Envoie une séquence de touches à l'élément.
- **`click()`** : Clique sur l'élément.
- **`submit()`** : Soumet le formulaire auquel l'élément appartient.
- **`getText()`** : Retourne le texte contenu dans l'élément.
- **`getAttribute(String name)`** : Retourne la valeur de l'attribut spécifié.
- **`isDisplayed()`** : Indique si l'élément est actuellement visible.
- **`isEnabled()`** : Indique si l'élément est activé.
- **`isSelected()`** : Indique si l'élément est sélectionné.

### Utilisation de `By` pour Localiser des Éléments

- **`By.id(String id)`** : Localise les éléments par leur attribut `id`.
- **`By.name(String name)`** : Localise les éléments par leur attribut `name`.
- **`By.className(String className)`** : Localise les éléments par leur nom de classe.
- **`By.tagName(String tagName)`** : Localise les éléments par leur nom de balise.
- **`By.cssSelector(String selector)`** : Localise les éléments par un sélecteur CSS.
- **`By.xpath(String xpathExpression)`** : Localise les éléments par une expression XPath.
- **`By.linkText(String linkText)`** : Localise les liens par leur texte visible.
- **`By.partialLinkText(String partialLinkText)`** : Localise les liens par une partie de leur texte visible.

### Exemples d'Utilisation

#### Ouvrir une Page et Rechercher un Élément

```java
WebDriver driver = new ChromeDriver();
driver.get("https://www.example.com");

WebElement element = driver.findElement(By.id("elementId"));
element.sendKeys("Hello, World!");
element.submit();

driver.quit();
```

#### Naviguer entre les Pages

```java
WebDriver driver = new ChromeDriver();
driver.get("https://www.example.com");

driver.navigate().to("https://www.anotherexample.com");
driver.navigate().back();
driver.navigate().forward();
driver.navigate().refresh();

driver.quit();
```

#### Gérer les Fenêtres et les Onglets

```java
WebDriver driver = new ChromeDriver();
driver.get("https://www.example.com");

String originalWindow = driver.getWindowHandle();
Set<String> allWindows = driver.getWindowHandles();

for (String windowHandle : allWindows) {
    driver.switchTo().window(windowHandle);
}

driver.switchTo().window(originalWindow);
driver.quit();
```

### Options et Gestion du Navigateur

#### Gérer les Cookies

```java
driver.manage().addCookie(new Cookie("key", "value"));
Cookie cookie = driver.manage().getCookieNamed("key");
driver.manage().deleteCookieNamed("key");
driver.manage().deleteAllCookies();
```

#### Gérer le Temps d'Attente

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementId")));
```

