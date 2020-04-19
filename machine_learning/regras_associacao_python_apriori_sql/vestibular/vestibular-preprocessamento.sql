
-- idade com dados incorretos
select * from socioeconomica where idade < 14 or idade > 80;
select avg(idade) from socioeconomica where idade > 14 and idade < 80;
update socioeconomica set idade = 20 where idade < 14 or idade > 80;