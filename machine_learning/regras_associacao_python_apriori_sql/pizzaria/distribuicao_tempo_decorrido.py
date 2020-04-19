# -*- coding: utf-8 -*-
"""
Created on Thu Apr 26 10:47:53 2018

@author: cborges
"""

import matplotlib.pyplot as plt
from numpy import genfromtxt

dados = genfromtxt("tempo_decorrido.csv")

histograma = plt.hist(dados, bins=5)