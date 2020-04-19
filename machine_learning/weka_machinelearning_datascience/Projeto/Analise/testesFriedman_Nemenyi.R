dados  <- read.csv('C:\\Programacao\\Java\\MachineLearning_Udemy\\Projeto\\Analise\\dados.csv')

#Pacotes para teste de Friedman e Nemenyi
if (!require("devtools"))
	install.packages("devtools")

install.packages("forecast",
                 repos = c("http://rstudio.org/_packages",
                           "http://cran.rstudio.com"), dependencies=TRUE)

devtools::install_github("trnnick/TStools")

matriz <- as.matrix(dados)

png(filename="C:\\Programacao\\Java\\MachineLearning_Udemy\\Projeto\\Analise\\NemenyiTestResult.png")

TStools::nemenyi(matriz, conf.int=0.95, plottype="vline")

dev.off()


