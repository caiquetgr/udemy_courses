# -*- coding: utf-8 -*-
"""
Created on Mon Apr 30 10:34:10 2018

@author: cborges
"""

from apyori import apriori
import pandas as pd

dados = pd.read_csv('dados_pizzaria_pos_transformacao.csv')
dados = dados.drop(['borda', 'refrigerante'], axis=1)

transacoes = []

for i in range(0, dados.shape[0]):
    transacoes.append([str(dados.values[i,j]) for j in range(0, dados.shape[1])])
    
resultado = apriori(transacoes, min_support=0.03, min_confidence=0.7, min_lift=1.2, min_length=2)

lista_resultado = [list(x) for x in resultado]

for i in range(0,5):
    print([list(x) for x in lista_resultado[i][2]])    