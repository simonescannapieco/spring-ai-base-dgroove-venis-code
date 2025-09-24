Dammi una definizione del termine <lemma>, come riporterebbe un dizionario. Restituisci la risposta in formato JSON, usando la minima formattazione possibile e senza markdown.
Il JSON deve avere la seguente struttura:
    - il lemma deve essere inserito come valore della chiave '''lemma''';
    - la definizione in stile formale e conciso deve essere inserita come valore della chiave '''definizione''';
    - esempi di utilizzo del lemma come strutture JSON annidate sotto la chiave 'esempi'. Le strutture JSON annidate devono riportare come unico valore la '''frase''' di esempio.