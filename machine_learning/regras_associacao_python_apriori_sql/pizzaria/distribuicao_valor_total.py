#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 24 21:31:09 2018

@author: caiqueborges
"""

import matplotlib.pyplot as plt
from numpy import genfromtxt

dados = genfromtxt('valor_total.csv')
#histograma = plt.hist(dados, bins='sturges')
#histograma = plt.hist(dados, bins='scott')
#histograma = plt.hist(dados, bins='fd') 
#histograma = plt.hist(dados, bins=5)
histograma = plt.hist(dados)