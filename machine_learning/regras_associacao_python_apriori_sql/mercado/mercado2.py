# -*- coding: utf-8 -*-
"""
Created on Tue Apr 10 22:39:08 2018

@author: cborges
"""

import pandas as pd

dados = pd.read_csv('mercado2.csv', header=None)

transacoes = []

for i in range(0, dados.shape[0]):
    transacoes.append( [str(dados.values[i][j]) for j in range(0,dados.shape[1])] )
    
transacoes[0][:]

from apyori import apriori

resultado = apriori(transacoes, min_support=0.003, min_confidence=0.2, min_lift=4.0, min_length=2)

lista = [list(x) for x in list(resultado)]

resultadoFormatado = []

for i in range(0,5):
    resultadoFormatado.append( [list(j) for j in lista[i][2]] )
    
resultadoFormatado