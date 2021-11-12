# uk223

Herzlich Willkommen zu unserem üK Projekt. Unsere Aufgabe ist es die Teilaufgabe 4 in unserem Projekt zu implementieren.
Dieses Projekt wurde von Anojan Pirabakaran und Enes Spahiu implementiert.

#Voraussetzungen
Seien Sie sicher, das Sie JAVA JDK VERSION 11 haben.
Wählen Sie in den "Project Structure" unter "Project SDK" JDK 11.

Sie werden sicher einen Docker Container brauchen. Auf dem Docker Container sollte PostgreSQL auf Port 5432 laufen.
Wenn Sie noch keinen haben, erstellen Sie einen mit diesem Command:
'docker run --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres'

Der Benutzername und das Password sind beide "postgres".
Wenn Sie Lust hätten das Password zu ändern, gehen Sie im Projekt unter "application.properties".

#Setup
Clonen Sie dieses Projekt auf ihren Rechner. Öffnen Sie das Projekt mit IntelliJ.
Wir haben Gradle schon heruntergeladen. Sie müssen nur die Gradle Funktion "bootRun" laufen lassen

Öffnen Sie Ihren Browser und geben Sie: localhost:8080/users/welcome ein.
Wenn ein Login Fenster auftaucht, geben Sie das ein:
ss: james
password: bond

Nun sehen Sie auf Ihrem Bildschirm "Hello World".

Stellen Sie im DBeaver eine neue Verbindung her.
Der Benutername und das Password sind "postgres".

#Probleme
- Wenn ein Problem mit groups_users auftaucht, starten Sie das Backend neu.
- Container neu starten und schauen ob der Container lauft.
- IntelliJ und Spring Boot neu starten.
- Connection zu DBeaver überprüfen.