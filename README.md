# Lokale Installation
Einige Schritte könnten bereits automatisch erfolgt sein. In Schritt 3 wird manchmal Glassfish nicht direkt angezeigt. Das System neu starten könnte abhilfe schaffen. Irgendwann taucht es dann von alleine auf. 

0. Glassfish laden (Backend->Glassfish-4.1.2.zip): https://drive.google.com/drive/folders/1rh7hl3w-4DVOCfcjfwYi4SxY9XDq9CQb?usp=sharing
0. Projekt aus GitHub clonen: https://github.com/Game-Engineering/imao-backend.git
1. File -> Import -> Maven -> Existing Maven Projects -> Next -> Browse -> .../imao-backend -> Ok -> Finish
2. Rechtsklick auf imao-backend -> Maven -> Update Project -> Haken bei imao-backend -> Ok
3. Window -> Preferences -> Server -> Runtime Environments -> Add -> GlassFish (Haken bei Create a new local server) -> Next ->
  -> Bei GlassFish location den heruntergeladenen Ordner auswählen (der Ordner, der den bin ordner enthält), z.B. C:\Users\shish\Downloads\glassfish-4.1.2\glassfish4
  -> Bei Java location den Ordner der installierten JDK auswählen, z.B. C:\Program Files\Java\jdk1.8.0_131
  -> Next -> Unten Haken bei Use JAR archives for deployment -> Finish -> Ok
4. Rechtsklick auf das Projekt (imao-backend) -> Properties -> Targeted Runtimes -> Haken bei GlassFish 4 -> Apply
5. Java Compiler -> Haken bei Enable project specific settings -> Kein Haken bei Use Compliance from... -> Compiler compliance level auf 1.8 stellen -> Apply and Close -> Yes (Wenn Projekt Fehler anzeigt: Schritt 2 wiederholen)
6. Rechtsklick auf das Projekt (imao-backend) -> Run as -> Run on Server -> GlassFish 4 auswählen -> Next -> Sicherstellen dass imao-backend in der rechten Spalte ist -> Finish
7. http://localhost:8080/imao/api/spiel/
  -> Ausgabe sollte sein "It works"


## IMAO

IMAO (International Medical Aid Organization) project at the Mannheim University of Applied Sciences in the lecture GAE

Der Code und die Commit history ist einfacher zu lesen wenn du dich an folgende Hinweise hälst.

Denke daran du arbeitest nicht alleine und jerder ist dir dankbar, wenn mehr Zeit zum lesen und verstehen verschwendet werden muss als zum Programmieren.

Versuche jeden nicht trivialen Codeblock und jede Methode zu kommentieren (Was tut sie? / Welche Paramether muss ich übergeben? / Was bekomme ich zurück? / Was könnte schiefgehen?)

Prof. Dr. Frank Dopatka [23.03.2018 18:23 Uhr via Slack]
**"deutschsprachige Dokumentation und Code" -> Ja bitte !!!!**


## Empfohlene Emojis

| Emoji | Raw Emoji Code | Description |
|:---:|:---:|---|
| 🎨 | `:art:` | Verbesserung von **Format/Struktur** des Codes |
| 📰 | `:newspaper:` | Erstellen einer **neuen Datei** |
| 📝 | `:pencil:` | **kleine Änderungen** am Code oder Text |
| 🐎 | `:racehorse:` | Verbesserung der **performance** |
| 📚 | `:books:` | Schreiben von **Kommentar** |
| 🐛 | `:bug:` | Melden eines **bug**, with `@FIXME` Comment Tag |
| 🚑 | `:ambulance:` | Beheben eines **bug** |
| 🔥 | `:fire:` | **Entfernen von Code** oder Datein |
| 🚜 | `:tractor:` | **Ändern der Dateistruktur**. Normalerweise zusammen mit `:art:` |
| :hammer: | `:hammer:` | when **refactoring** code |
| :umbrella: | `:umbrella:` | Hinzufügen von **tests** |
| :microscope: | `:microscope:` | when adding **code coverage** |
| :lock: | `:lock:` | when dealing with **security** |
| 💄 | `:lipstick:` | when improving **UI**/Cosmetic |
| 🚧 | `:construction:` | **WIP**(Work In Progress) Commits, _maybe_ with `@REVIEW` Comment Tag |
| 💎 | `:gem:` | New **Release** |
| :bookmark: | `:bookmark:` | Version **Tags** |
| 🎉 | `:tada:` | **Initial** Commit |
| :speaker: | `:speaker:` | when Adding **Logging** |
| :mute: | `:mute:` | when Reducing **Logging** |
| ✨ | `:sparkles:` | **Neue Funktion** |
| :bulb: | `:bulb:` | Neue **Idea**, mit `@IDEA` Comment Tag |
| :ribbon: | `:ribbon:`| Vom **Kunden** gewünschte Anpassungen |
| :snowflake: | `:snowflake:` | changing **Configuration**, Usually together with :penguin: or :ribbon: or :rocket: |
| :bank: | `:bank:` | **Generic Database** specific (Migrations, Scripts, Extensions, ...) |
| 🤝 | `:handshake:` | **Merge files** |



## Verfügbare Grammatik

- `@TODO`: Wenn etwas getan werden muss
- `@FIXME`: Fehler / Error, sollte behoben werden, markiert mit 🐛 Commit
- `@XXX`: Warne andere Programmierer vor problematischem oder irreführenden Code
- `@IDEA`: Eine neue Idee, markiert mit 💡 Commit
- `@HACK`: Kundenanpassung, markiert mit 🎀 Commit
- `@NOTE`: Ein Hinweis auf etwas wichtiges
- `@REVIEW`: Muss geprüft werden, normalerweise markiert mit 🚧 Commit
