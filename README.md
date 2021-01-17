# Monitorizare meteo

Aplicatie de monitorizare meteo

## Descriere

Aplicatia este utilizata pentru monitorizarea informatiilor 
meteorologice dintr-un oras al unei tari, ales de utilizator. Datele despre orase,pentru 
localizare, sunt incarcate dintr-un fisier. Pe baza acestor informatii , sunt transmise
cereri catre https://openweathermap.org/api , iar raspunsul este in format JSON, si contine
datele exacte cu privire la conditiile meteo din orasul respectiv. Interfata grafica a fost
realizata cu ajutorul JavaFX utilizand SceneBuilder.

## Utilizare

Aplicatia de fata presupune selectarea de catre utilizator a unei tari, si a unui oras din 
tara respectiva. Ulterior, dupa aceasta alegere, vor fi afisate urmatoare informatii:

1. Numele orasului selectat
2. Informatii cu privire la data si ora curenta
3. Descrierea conditiilor meteorologice
4. Pictograma reprezentativa conditiilor meteo actuale
5. Temperatura 
6. Temperatura minima si maxima din ziua respectiva
7. Presiunea atmosferica
8. Umiditatea aerului
9. Viteza vantului
10. Informatii cu privire la ora rasaritului si apusului.

De asemenea, utilizatorul poate selecta daca doreste ca temperatura sa ii fie afisata in
grade Celsius sau grade Fahrenheit.

## Limbajul folosit

Java JDK 15

## Realizator

Gutiu Dan-Cristian
