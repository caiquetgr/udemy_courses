#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Dec 23 22:07:15 2018

@author: caique
"""

import pandas as pd

base = pd.read_csv('credit-data.csv')
base.describe()

#há registros com age negativo
base.loc[base['age'] < 0]

#soluções:
#apagar a coluna inteira
base.drop('age', 1, inplace=True)
#apagar as linhas com problema
base.drop(base[base.age < 0].index, inplace=True)
#preencher os valores manualmente
#preenhcer os valores com a média
base.mean()
base['age'].mean()
base['age'][base.age > 0].mean()
base.loc[base.age < 0, 'age'] = base['age'][base.age > 0].mean()


#  valores faltando
pd.isnull(base['age'])
base.loc[pd.isnull(base['age'])]

# : -> todas as linhas
# da coluna 1 até 3                        
previsores = base.iloc[:, 1:4]
classe = base.iloc[:, 4]

from sklearn.preprocessing import Imputer

imputer = Imputer(missing_values='NaN', strategy='mean')
imputer = imputer.fit(previsores)
previsores = imputer.transform(previsores)

# padronização de dados
from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
previsores = scaler.fit_transform(previsores)


