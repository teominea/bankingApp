[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/iitdRc-l)

### *Proiect 2024 - Minea Teodora-Maria, 321CB*

**Descrierea proiectului**

##### _Proiectul EBanking App constă în dezvoltarea unei aplicații de banking care să ofere unei persoane următoarele funcționalități:_
- Crearea unui profil de utilizator în cadrul aplicației
- Crearea unui cont bancar
- Efectuarea de tranzacții bancare (depuneri monetare, transferuri între conturi de diferite monede, transferuri către alte conturi bancare)
- Adăugarea unor ”prieteni” către care vor fi făcute transferuri bancare
- Cumpărarea de acțiuni
- Vizualizarea recomandarilor de acțiuni

#### _Un supervizor al aplicației va putea să vizualizeze următoarele informații:_
- Portofoliul utilizatorilor (conturi bancare, acțiuni)
- Profilul utilizatorilor (nume, prenume, email, adresa, prieteni in aplicatie)
- Incercările eșuate de accesare a anumitor funcționalități ale aplicației

#### Pentru a dezvolta această aplicație, am folosit următoarele Design Patterns:

- Singleton:
    - Pentru a asigura că aplicația de banking are o singură instanță pe parcursul efectuării operațiunilor, am folosit Singleton Pattern.
    - De fiecare dată când vom apela metoda getInstance(), vom primi aceeași instanță a clasei, ceea ce va împiedica crearea mai multor obiecte de același tip și va asigura că toate operațiunile se vor face pe aceeași instanță a clasei.
- Builder:
    - Pentru a crea un obiect de tip User, am folosit Builder Pattern.
    - Acesta ne permite să construim obiecte complexe pas cu pas, fără a fi nevoie să avem constructori cu un număr mare de parametri.
    - În cazul aplicației curente, am folosit acest pattern pentru a construi un obiect de tip User, care are un număr mare de atribute (nume, prenume, email, adresă, prieteni, portofoliu), fiind mai ușor de gestionat pe parcursul creării obiectului.
    - În cadrul comenzii CreateUser, dacă utilizatorul nu a fost încă înrolat vom crea o instanță a clasei UserBuilder, care ne va ajuta să construim un obiect de tip User pas cu pas.
- Observer:
  - Pentru a putea urmări schimbările din cadrul aplicatiei, am folosit Observer Pattern.
  - Clasa Command va fi subiectul, iar clasa BankObserver va fi observerul.
  - In cadrul metodei execute() din clasa Command, vom notifica observerul de fiecare dată când se va efectua o operațiune în cadrul aplicației.
  - Astfel, de fiecare dată când se va modifica mesajul returnat de comandă (fie el de eroare sau cu scop informativ - listare user, portofoliu sau recomandare de acțiuni), observerul va fi notificat și va afișa mesajul corespunzător la consolă.
- Command:
  - Pentru a putea efectua operațiuni în cadrul aplicației, am folosit Command Pattern.
  - Clasa abstractă Command va fi moștenită de clasele corespunzătoare fiecărei operațiuni (CreateUser, CreateAccount, AddMoney, ExchangeMoney, TransferMoney, AddFriend, BuyStock, ListUsers, ListPortfolio, RecommendStocks, BuyPremium).
  - Fiecare clasă concretă va implementa metoda execute(), care va efectua operațiunea corespunzătoare.
  - De asemenea, clasa command va avea un atribut de tip BankObserver, care va fi notificat de fiecare dată când se va efectua o operațiune în cadrul aplicației și metodele attach(BankObserver observer) și notifyObserver().
- Factory:
    - Pentru a putea parsa fișierele de bază ale aplicației, adică exchangeRates.csv și stockValues.csv, fundamentale pentru anumite operațiuni, am folosit Factory Pattern.
    - Interfata MapFromFile va fi implementată de clasele ExchangeRates și Stocks. Acestea vor avea ca parametru pentru constructor numele fișierului din care se vor citi datele.
    - Interfata MapFromFile va avea metoda abstractă parseFile(), care va fi implementată în clasele concrete. Aceasta metoda va returna un tip de date generic, intrucât vom crea un Map care va primi ca parametri cheia de tip generic T si valorile de tip wildcard.
    - Cele două clase concrete vor implementa metoda parseFile și vor returna Map<String, ArrayList<Double>> pentru Stocks si Map<Currency, Map<Currency, Double>> pentru ExchangeRates.
    - In cadrul clasei BankingApp vom implementa metoda getMapFromFile, care va primi ca parametru numele fișierului și va returna un Map, în funcție de tipul de date pe care îl așteptăm să îl citim.
    - Apoi, când apelăm prima (și singura) dată constructorul clasei, vom parsa fișierele, apelând metoda parseFile

În cadrul aplicației, am declarat următoarele clase:

- BankingApp: clasa principală a aplicației, care va conține un map de useri, două obiecte de tip MapFromFile (exchangeRates și stocks) și instanța clasei BankObserver.
  - Constructorul va parsa fișierele exchangeRates.csv și stockValues.csv, apelând metoda getMapFromFile și va inițializa map-ul de useri.
  - Metoda getInstace() va returna instanța clasei, folosind Singleton Pattern.
  - Metoda cleanup() va șterge toate informațiile care ar putea să rămână de la un test anterior, adică va seta instanța pe null și va șterge toate datele din map-ul de useri.
  - Metodele de get si set pentru map-urile de exchangeRates și stocks vor fi folosite pentru a obtine informatiile dorite.
- User: clasa care va reprezenta un utilizator al aplicației, având următoarele atribute: nume, prenume, email, adresă, prieteni, portofoliu.
  - Metodele de get si set pentru fiecare atribut vor fi folosite pentru a obtine informatiile dorite, necesare în cadrul implementării UserBuilder.
  - Metoda upgrade() care va fi folosită pentru a upgrada un utilizator la un cont premium (seteaza atributul premium la true).
- UserBuilder: clasa care va fi folosită pentru a construi un obiect de tip User, folosind Builder Pattern.
  - Metodele cu parametri pentru fiecare atribut al clasei User vor fi folosite pentru a construi obiectul pas cu pas (se va atribui câte un câmp pe rând).
  - Metoda build() va returna obiectul de tip User construit.
- Portfolio: clasa care va reprezenta portofoliul unui utilizator, având următoarele atribute: conturi bancare, acțiuni.
  - Constructorul clasei va initializa cele două map-uri, care vor fi folosite pentru a adăuga conturi bancare și acțiuni.
- Stocks: clasa care va fi folosită pentru a parsa fișierul stockValues.csv, folosind Factory Pattern.
  - Metoda parseFile() va returna un Map<String, ArrayList<Double>>, care va fi folosit pentru a obține valorile acțiunilor.
- ExchangeRates: clasa care va fi folosită pentru a parsa fișierul exchangeRates.csv, folosind Factory Pattern.
  - Metoda parseFile() va returna un Map<Currency, Map<Currency, Double>>, care va fi folosit pentru a obține ratele de schimb.
- Account: clasa care va reprezenta un cont bancar, având următoarele atribute: Currency currency (va fi una dintre monedele din enum Currency), Double balance (suma de bani din cont).
  - Metodele de get si set pentru fiecare atribut vor fi folosite pentru a obtine informatiile dorite.
  - Constructorul clasei va primi ca parametri moneda și suma de bani din cont.
- BankObserver: clasa care va fi folosită pentru a notifica utilizatorul de fiecare dată când se va efectua o operațiune în cadrul aplicației, folosind Observer Pattern.
  - Metoda update() va fi folosită pentru a afișa mesajul corespunzător la consolă, iar subject va fi de tip Command.
- Command: clasa abstractă care va fi folosită pentru a efectua operațiuni în cadrul aplicației, folosind Command Pattern.
  - Contine atributele message și observer, metodele abstracte execute() și notifyObserver() și metoda attach(BankObserver observer).
- CommandAddAcount: clasa care va fi folosită pentru a crea un cont bancar, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de creare a unui cont bancar. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).   
      - Vom verifica dacă utilizatorul există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom verifica dacă contul există deja în map-ul de conturi bancare al utilizatorului, iar dacă există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă contul nu există, vom crea un obiect de tip Account și îl vom adăuga în map-ul de conturi bancare al utilizatorului.
- CommandAddFriend: clasa care va fi folosită pentru a adăuga un prieten, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de adăugare a unui prieten. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul si prietenul pe care dorim să îl adăugăm există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom verifica dacă prietenul există deja în lista de prieteni a utilizatorului, iar dacă există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă prietenul nu există, vom adăuga prietenul în lista de prieteni a utilizatorului.
- CommandAddMoney: clasa care va fi folosită pentru a adăuga bani în cont, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de adăugare a unei sume de bani în cont. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul și contul bancar există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul și contul bancar există, vom adăuga suma de bani în contul bancar al utilizatorului.
- CommandBuyPremium: clasa care va fi folosită pentru a upgrada un utilizator la un cont premium, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de upgradare a unui utilizator la un cont premium. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom încerca să extragem 100 USD din contul bancar pentru a plăti upgrade-ul la cont premium, iar daca nu are suficienți bani, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom apela metoda upgrade() pentru a upgrada utilizatorul la un cont premium.
- CommandBuyStock: clasa care va fi folosită pentru a cumpăra acțiuni, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de cumpărare a unor acțiuni. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul și acțiunea există în map-ul de useri și în map-ul de acțiuni, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul și acțiunea există, vom verifica dacă utilizatorul are suficienți bani pentru a cumpăra acțiunile, iar dacă nu are, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul are suficienți bani, vom adăuga acțiunile în map-ul de acțiuni al utilizatorului și vom scădea suma de bani din contul bancar al utilizatorului.
      - Dacă utilizatorul este de tip premium, va primi o reducere de 5% din pretul acțiunilor cumpărate.
- CommandCreateUser: clasa care va fi folosită pentru a crea un utilizator, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de creare a unui utilizator. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul există deja în map-ul de useri, iar dacă există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul nu există, vom crea un obiect de tip UserBuilder și îl vom folosi pentru a construi un obiect de tip User.
      - Apoi, vom adăuga utilizatorul în map-ul de useri.
- CommandExchangeMoney: clasa care va fi folosită pentru a schimba bani dintr-o monedă în alta, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de schimbare a unei sume de bani dintr-o monedă în alta. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul și contul bancar există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul și contul bancar există, vom verifica dacă există un cont pentru moneda în care vrem să facem schimbul, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă monedele există, vom schimba suma de bani dintr-o monedă în alta, folosind ratele de schimb - înmulțim suma de bani cu rata de schimb și scădem comisionul de 1% dacă suma depășește 50% din suma actuală, doar dacă utilizatorul nu este de tip premium.
- CommandTransferMoney: clasa care va fi folosită pentru a transfera bani dintr-un cont în altul, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de transfer a unei sume de bani dintr-un cont în altul. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul și conturile bancare există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul și conturile bancare există, vom verifica daca utilizatorul este prieten cu cel căruia dorește să îi facă transferul, iar dacă nu este, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul și conturile bancare există, vom verifica dacă există suficienți bani în contul sursă, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă există suficienți bani, vom transfera suma de bani din contul sursă în contul destinație.
- CommandListPortfolio: clasa care va fi folosită pentru a lista portofoliul unui utilizator, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de listare a portofoliului unui utilizator. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom afișa portofoliul utilizatorului, parsând listele din portofoliu și reținând informațiile într-un string de tip json, pe care îl vom transmite observerului.
- CommandListUser: clasa care va fi folosită pentru a lista un utilizator, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de listare a unui utilizator. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom afișa informațiile despre utilizator, parsând lista de prieteni și reținând informațiile într-un string de tip json, pe care îl vom transmite observerului.
- CommandRecommendStocks: clasa care va fi folosită pentru a recomanda acțiuni, moștenind clasa Command.
  - Metoda execute() va fi folosită pentru a efectua operațiunea de recomandare a unor acțiuni. In cadrul acestei metode:
      - Vom atasa observerul la comandă, folosind metoda attach(BankObserver observer).
      - Vom verifica dacă utilizatorul există în map-ul de useri, iar dacă nu există, vom folosi metoda setMessage() pentru a notifica observerul de eroare.
      - Dacă utilizatorul există, vom parcurge lista de acțiuni și vom recomanda acțiunile care au media valorii din ultimele 5 zile mai mare decât media valorii din ultimele 10 de zile, prin metoda setMessage().
- Main: clasa care va fi folosită pentru a rula aplicația.
  - In cadrul metodei main, vom șterge toate informațiile care ar putea să rămână de la un test anterior, iar apoi vom parcurge fișierul ”commands.txt” și vom executa comenzile corespunzătoare, folosind metoda execute() a clasei Command, pentru fiecare instanță a clasei copil.
  

Concepte de POO folosite:

  - Abstractizare: am folosit clase abstracte pentru a defini comportamentul comun al comenzilor și pentru a transmite metoda execute() în clasele copil. De asemenea, pentru parcurgerea fișierelor exchangeRates.csv și stockValues.csv, am folosit o interfață MapFromFile care conține metoda abstracta parseFile, care a fost implementată de clasele ExchangeRates și Stocks.
  - Moștenire: am folosit moștenirea pentru a defini comportamentul comun al comenzilor în clasa abstractă Command și pentru a defini comportamentul specific al fiecărei comenzi în clasele copil.
  - Polimorfism: am folosit polimorfismul pentru a executa metoda execute() a clasei Command, pentru fiecare instanță a clasei copil, dar și pentru a parsa fișierele exchangeRates.csv și stockValues.csv, folosind metoda parseFile a interfeței MapFromFile.
  - Încapsulare: am folosit încapsularea pentru a proteja datele și pentru a oferi acces la acestea doar prin metodele de get si set, în majoritatea claselor din cadrul aplicației.

**BONUS:**
  Functionalitatea opțiunii Premium este detaliată în descrierea claselor.