# Teque1.java
    Teque er første oppgave, hvor jeg har valgt å bruke noder for å lage en lenket liste med noder med metodene push_back(), push_front(), push_middel() og get().
    Input er fra system.in, og den første inputen man skriver inn er antall kommandoer som skal utføres, slik at koden kun leser x antall inputer.

# Sortering.java
    Input kommer fra fillesning der filnavnet kommer fra kommandolinjeargument, på følgende form: xxxxxxx_10.txt, 
    for at koden skal kjøre må antall tall i filen komme etter siste _(understrek).
    Sortering.java lager en ny .txt fil som viser den nå sorterte listen ved bruk av insertion sort(dette kan enkelt endres til mergeSort).
    Sortering.java lager også en .csv fil som viser statistikken over antall bytter, antall sammenligninger og tidsbruk for begge sorteringsalgoritmene for
    n + 1 sorteringer. Derfor er denne filen ikke egnet til bruk på datasett med stor n, fordi den må lage og sortere n+1 lister.

# AlgoritmeTest.java
    AlgoritmeTest.java er i stor grad lik som Sortering.java. Hovedforskjellen er at den lager en .csv fil med statistikk for begge algoritmene kun for en liste med n elementer.
    Dette er slik at man skal kunne sammenligne hvordan de to sorteringsalgoritmene opptrer for datasett med stor n. Dersom man ønsker å gjøre dette burde man bruke denne koden.

# Sammenligninger og bytter
    Sammenligniger er beregnet slik at de telles hver gang algoritmen sammenligner to tall uavhengig om den bytter om på tallene.
    Bytter er beregnet slik at dersom algoritmen bytter plass på to tall så øker byttene. I mergeSort er det slik at bytter kun regnes når algoritmen må endre plasser på sublistene før den setter listene sammen igjen.

# Svar på skriveoppgaver ligger på filen SkriftligOppgave.txt