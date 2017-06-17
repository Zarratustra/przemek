# Przemek
Produkt rejestrujący zapotrzebowanie na ekspertyzę medyczną eliminując kolejki

## Uruchamianie Dockera
Jest wbrew pozorom nietrywialne. Aby wygenerować obraz:
`./gradlew build buildDocker` Aby go uruchomić: `docker run --user postgres -p 8080:8080  agh/przemek:1.0-SNAPSHOT 
`