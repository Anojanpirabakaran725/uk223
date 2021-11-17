# üK Repository M223

Herzlich Willkommen zu unserem üK Projekt. Unsere Aufgabe ist es die Teilaufgabe 4 in unserem Projekt zu implementieren.
Dieses Projekt wurde von Anojan Pirabakaran und Enes Spahiu implementiert.

# Projektbeschreibung
Unser Auftrag war es Group Modelle zu erstellen. Diese Gruppen sollte Informationen von Usern anzeigen. Der User kann nur in einer Gruppe gleichzeitig Mitglied sein.
Wir haben Endpoints erstellt, für die einzelnen CRUD-Operationen an Gruppen zu machen. Bei der GET-Funktion können Sie mit Pagination alle Mitglieder der Gruppe auflisten. 

Ein Admin-User kann eine Gruppe erstellen, bearbeiten und löschen. Ein Admin oder die Mitglieder einer Gruppe können Informationen der Gruppe anschauen, bearbeiten und löschen. Wir haben auch Endpoints für die typischen CRUD-Operation für User gemacht. 

Sie können sich einen User erstellen, bearbeiten, anschauen und löschen. Alle Users haben verschiedene Rollen und Autoritäten. 

# Voraussetzungen
Seien Sie sicher, das Sie JAVA JDK VERSION 11 haben.
Wählen Sie in den "Project Structure" unter "Project SDK" JDK 11.

Sie werden sicher einen Docker Container brauchen. Auf dem Docker Container sollte PostgreSQL auf Port 5432 laufen.
Wenn Sie noch keinen haben, erstellen Sie einen mit diesem Command:
`docker run --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres`

Der Benutzername und das Password sind beide "postgres".
Wenn Sie Lust hätten das Password zu ändern, gehen Sie im Projekt unter "application.properties".


# Setup
Clonen Sie dieses Projekt auf ihren Rechner. Öffnen Sie das Projekt mit IntelliJ.
Da Gradle noch nicht heruntergeladen ist, müssen Sie einen Build & Run vornehmen. Drücken Sie im IntelliJ auf das grüne Play Button.
Nun sollte Gradle heruntergeladen sein, und es sollte kein Fehler mehr im Programm auftauchen. Nun öffnen Sie die Gradle Funktionen und wählen Sie hier unter "Tasks/application" die Funktion "bootRun".
Jetzt wird das Backend richtig gestartet. Stellen Sie sicher das der Docker-Container lauft.

Nun müssen Sie eine neue Verbindung anlegen. Drücken Sie oben auf das Kabel und wählen Sie nun PostgreSQL. Nun erscheint ein kleines Fenster und geben Sie dort nun den DB-Username und Passwort ein. Nun sind sie mit dem DBeaver verbunden.
Der Benutername und das Password sind "postgres".

Öffnen Sie Ihren Browser und geben Sie: localhost:8080/users/welcome ein.
Wenn ein Login Fenster auftaucht, geben Sie das ein:


benutzername: james     
password: bond

Nun sehen Sie auf Ihrem Bildschirm "Hello World".


# Probleme
- Wenn ein Problem mit groups_users auftaucht, starten Sie das Backend neu.
- Container neu starten und schauen ob der Container lauft.
- IntelliJ und Spring Boot neu starten.
- Connection zu DBeaver überprüfen.

- Wenn Sie ein JDBC / Hibernate Fehler haben, starten Sie den Docker-Container.

