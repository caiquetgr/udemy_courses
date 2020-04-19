#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 28 13:12:56 2018

@author: caique
"""

import pandas as pd

base = pd.read_csv("census.csv")

previsores = base.iloc[:, 0:14].values
classe = base.iloc[:, 14].values

from sklearn.preprocessing import LabelEncoder, OneHotEncoder

labelencoder_previsores = LabelEncoder()

previsores[:,1] = labelencoder_previsores.fit_transform(previsores[:, 1])
previsores[:,3] = labelencoder_previsores.fit_transform(previsores[:, 3])
previsores[:,5] = labelencoder_previsores.fit_transform(previsores[:, 5])
previsores[:,6] = labelencoder_previsores.fit_transform(previsores[:, 6])
previsores[:,7] = labelencoder_previsores.fit_transform(previsores[:, 7])
previsores[:,8] = labelencoder_previsores.fit_transform(previsores[:, 8])
previsores[:,9] = labelencoder_previsores.fit_transform(previsores[:, 9])
previsores[:,13] = labelencoder_previsores.fit_transform(previsores[:, 13])

# utilizar variáveis dummy para evitar problemas nas variáveis nominais, ex:
# 0 - branco, 1 - preto | 1 > 0 na multiplicação ou adição do algorítmo

one_hot_encoder = OneHotEncoder(categorical_features=[1,3,5,6,7,8,9,13])
previsores = one_hot_encoder.fit_transform(previsores).toarray()


labelencoder_classe = LabelEncoder()
classe = labelencoder_classe.fit_transform(classe)


from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
previsores = scaler.fit_transform(previsores)


