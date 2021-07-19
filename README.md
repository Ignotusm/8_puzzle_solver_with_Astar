# 8_puzzle_solver_with_Astar

Našou úlohou je nájsť riešenie 8-hlavolamu. Hlavolam je zložený z 8 očíslovaných
políčok a jedného prázdneho miesta. Políčka je možné presúvať hore, dole, vľavo
alebo vpravo, ale len ak je tým smerom medzera. Je vždy daná nejaká
východisková a nejaká cieľová pozícia a je potrebné nájsť postupnosť krokov,ktoré vedú z východiskovej do cieľovej pozície.


Riesenie : 

Stav : stav je reprezentovaný v 2D poli typu short.
Voľný štvorec je „0“ a ostatné štvorce sú čísla.
Operátory : Vpravo, Dole, Vľavo, Hore.
Heuristická funkcia: na heuristiku som používal dva metriky:
1. Počet políčok, ktoré nie sú na svojom mieste
2. Súčet vzdialenosti jednotlivých políčok od ich cieľovej pozície.  
Uzol : Uzol obsahuje informácie : -STAV
 -Parent
 -Posledne použitý operátor
-Číslo krokov od začiatku
 -Odhad ceny od začiatku cesty do cieľa
 
 Algoritmus:
 
 1. Vytvorí počiatočný uzol a umiestní ho medzi vytvorené a zatiaľ nespracované 
uzly
2. Ak neexistuje žiadny vytvorený a zatiaľ nespracovaný uzol, skončí s 
neúspechom – riešenie neexistuje
3. Vyberie najlacnejší uzol z vytvorených a zatiaľ nespracovaných, označí
ho aktuálny
4. Ak tento uzol predstavuje cieľový stav, skončí s úspechom – vypíše riešenie
5. Vytvorí nasledovníkov aktuálneho uzla a zaradí ich medzi spracované uzly
6. Nasledovníkov kontroluje, či už existuju medzi vytvorenými a zatiaľ
nespracovanými uzlami, keď nie, tak ich umiestní do vytvorených 
a zatiaľ nespracovaných, pokiaľ áno, tak kontroluje, či teraz 
vytvorený uzol má lacnejšiu cestu, a keď áno, tak umiestní do 
vytvorených a zatiaľ nespracovaných.
7. Choď na krok 2.

Vysledok :

Začiatok : 0 1 2 3 4 5

Cieľový stav : 3 4 5 0 1 2

Čas : 7 ms

Celkový počet uzlov : 327

Výstup :
1. DOWN
2. RIGHT
3. RIGHT
4. UP
5. LEFT
6. DOWN
7. LEFT
8. UP
9. RIGHT
10. RIGHT
11. DOWN
12. LEFT
13. UP
14. LEFT
15. DOWN
16. RIGHT
17. RIGHT
18. UP
19. LEFT
20. DOWN
21. LEFT

Dĺžka cesty : 21
