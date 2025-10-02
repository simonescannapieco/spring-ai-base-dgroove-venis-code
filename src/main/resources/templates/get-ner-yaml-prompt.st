ESEMPIO:
'''Titolo''' La partigiana Rosani e il benefattore Forti, il rabbino: «Preghiamo per la pace globale, finisca il dolore di tutti»
'''Sottotitolo''' Le parole del rabbino Corinaldi al cimitero ebraico in occasione della commemorazione di Achille Forti e Rita Rosani e dell'avvio del Yom Kippur, il giorno dell'espiazione.
'''Corpo''' <<Stiamo vivendo momenti importanti, che speriamo possano cambiare la situazione e portare a un nuovo anno migliore di quelli vissuti negli ultimi due. Oggi preghiamo per il popolo ebraico ma anche per tutto il mondo, a cui vogliamo bene. Cerchiamo di vivere in serenità, in pace>>.
Le parole sono del rabbino di Verona, Tomer Corinaldi, pronunciate al cimitero ebraico, in occasione della commemorazione del benefattore del Comune di Verona, Achille Forti, e della Medaglia d'oro Rita Rosani.
Oggi iniziano le 25 ore di digiuno totale e di preghiera per gli ebrei, in occasione dello Yom Kippur, il giorno dell'espiazione.
<<Confidiamo che potremo forse raggiungere la pace nella terra di Israele e per il popolo palestinese. Preghiamo per assicurare la sicurezza di tutti gli abitanti della terra. Gli ostaggi che stanno nel tunnel di Gaza da oltre due anni potrebbero finalmente tornare a casa, continueremo a pregare perché ciò avvenga e per la pace globale, perché finisca questo dolore di tutti>>.
Alla cerimonia è intervenuto anche l'assessore ai diritti umani, Jacopo Buffolo, ricordando il benefattore della città, Achille Forti, a cui è riservato spazio anche nella mostra ancora in corso a Castelvecchio, e la partigiana triestina Rita Rosani, caduta sui nostri monti. 
<<Rita come molte altre ragazze è caduta per la libertà e la pace>>, ha detto Buffolo, <<ciò a cui dobbiamo rivolgere principalmente la mente in questo momento>>.
<<Le 25 ore di digiuno non sono lo scopo principale dello Yom Kippur, che è piuttosto un invito alla riflessione e al ripensamento della nostra vita>>, è intervenuta la vicepresidente della comunità ebraica, Ester Silvana Israel.
<<Il nostro augurio è di una pace giusta e duratura>>.
'''YAML'''
```
ner:
    persona:
    - Tomer Corinaldi
    - Achille Forti
    - Rita Rosani
    - Jacopo Buffolo
    - Ester Silvana Israel
    luogo:
    - Verona
    - Castelvecchio
    - Israele
    - Gaza
    festività:
    - Yom Kippur
    data:
    organizzazione:
    orari:
    valuta:
    percentuale:
    quantità:
    - 25 ore
```

ESEMPIO:
'''Titolo''' Un nuovo caso di Chikungunya al via disinfestazioni in Borgo Milano, Saval, Stadio e Santa Lucia: tutte le vie
'''Sottotitolo''' Le operazioni, nelle serate di oggi mercoledì 1 ottobre, e a seguire, giovedì 2 e venerdì 3 ottobre.
'''Corpo''' Al via da questa sera, 1° ottobre, dalle ore 20, una nuova attività di disinfestazione nelle aree di Santa Lucia e Stadio/Borgo Milano.
L'intervento si rende necessario, su richiesta giunta oggi dall'Azienda Ulss 9 Scaligera, seguito della segnalazione di un caso umano autoctono di virus Chikungunya e del permanere di alcuni focolai larvali di zanzare.  
Il Sindaco di Verona ha pertanto emesso due ordinanze necessarie per agevolare le  attività di disinfestazione, che si svolgeranno, a partire dalle ore 20 fino al completamento delle operazioni, nelle serate di oggi mercoledì 1 ottobre, e a seguire, giovedì 2 e venerdì 3 ottobre.  
Attività sospese e le vie interessate
Nelle zone di trattamento le attività all'esterno sono sospese per tutta la durata delle operazioni.
Saranno effettuati anche i trattamenti larvicidi e di eliminazione dei focolai larvali. La cittadinanza è invitata a collaborare con gli operatori.
L'area di intervento, individuata dall'Ulss 9 Scaligera, include le seguenti vie in tutto o in parte, identificate in un raggio di azione di 300 metri.
Nel quartiere di Santa Lucia, le vie:
via Mons. Lorenzo Bellomi,
via Albere,
via Brigata Casale,
via Castelfidardo,
via Cavalchina,
via Sommacampagna,
via Brigata Acqui,
via Cacciatori delle Alpi,
via Brigata Cuneo,
via Ghetto,
via Brigata Guardie,
via Alessandro Lamarmora,
via Generale Passalacqua,
via Brigata Composta,
via Don Enrico Girardi,
via Mantovana,
via S. Elisabetta,
via Salasco,
via Generale Bava,
via VI Maggio,
via Generale Franzini,
via Agostino Guerrieri,
via Colombara.
'''YAML'''
```
ner:
    persona:
    - Lorenzo Bellomi (via),
    - Alessandro Lamarmora (via),
    - Azzo Passalacqua (via),
    - Enrico Girardi (via),
    - Santa Elisabetta (via),
    - Generale Eusebio Bava (via),
    - Generale Paolo Franzini Tibaldeo (via),
    - Agostino Guerrieri (via)
    luogo:
    - Saval
    - Santa Lucia
    - Stadio
    - Borgo Milano
    - via Mons. Lorenzo Bellomi,
    - via Albere,
    - via Brigata Casale,
    - via Castelfidardo,
    - via Cavalchina,
    - via Sommacampagna,
    - via Brigata Acqui,
    - via Cacciatori delle Alpi,
    - via Brigata Cuneo,
    - via Ghetto,
    - via Brigata Guardie,
    - via Alessandro Lamarmora,
    - via Generale Passalacqua,
    - via Brigata Composta,
    - via Don Enrico Girardi,
    - via Mantovana,
    - via S. Elisabetta,
    - via Salasco,
    - via Generale Bava,
    - via VI Maggio,
    - via Generale Franzini,
    - via Agostino Guerrieri
    - via Colombara.
    festività:
    data:
    - mercoledì 01/10/2025
    - giovedì 02/10/2025
    - venerdì 03/10/2025
    - VI Maggio (via)
    organizzazione:
    - Ulss 9 Scaligera
    orari:
    - 20:00
    valuta:
    percentuale:
    quantità:
    - 300 metri
```

Serializza le informazioni NER da articoli giornalistici in formato YAML.
Riporta e completa le informazioni di persone con nome e cognome.
Riporta le informazioni di luoghi intitolati a personaggi famosi con nome e cognome.
Riporta le date in formato DD/MM/YYYY.
Riporta gli orari in formato HH:MM.

ESEMPIO:
'''Titolo''' {titolo}
'''Sottotitolo''' {sottotitolo}
'''Corpo''' {corpo}
'''YAML'''
