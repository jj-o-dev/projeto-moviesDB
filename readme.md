# Resumo do projeto
**Uma aplicação Java com interface CLI que faz conexão com
banco de dados MySQL remoto**
(encontrei em [freesqldatabase.com](projeto-moviesDB.jar), é grátis)
**usando JDBC.**

**A interface faz 4 operações**:

**SELECT**  para exibir todos os dados
formatados

**INSERT**  de novos dados

**UPDATE** do dado de uma coluna, linha selecionada pelo _id_

e **DELETE** selecionado pelo _id_

O banco possui apenas uma tabela para armazenar filmes que gosto.
A tabela possui as colunas: **id** (_Primary-key_ com AUTO INCREMENT),
**titulo**, **ano**, **diretor** e **genero**.

## Caso queira testar

Basta dar um git clone no repositório ou baixar ele todo em .zip e quando
estiver com a pasta, **é só clicar duas vezes no arquivo** `executar.bat`. 

Ou pode abrir o 
terminal de sua preferência na pasta correta e digitar:

`java -jar projeto-moviesDB.jar`


**Detalhe importante: precisa ter o Java JRE versão 17 ou mais recente
instalado**.

(Testei apenas em ambiente Windows, não sei se funciona em Linux/Mac)

## Considerações finais

Eu comecei esse projeto para praticar mais e deixar exibido como início
de um portfólio (que vou preencher mais com o tempo), fiquei bastante 
contente por ter conseguido dar vida a minha ideia inicial. Aprendi
bastante durante o processo e espero poder aprender ainda mais nos próximos.

Sujestões de melhoria e críticas construtivas são bem vindas.

Obrigado pela atenção! 😁🖖☕