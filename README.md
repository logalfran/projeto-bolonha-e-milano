
# Bolonha e Milano

## Descrição do projeto

Bolonha e Milano é um jogo de administração de cidades medievais desenvolvido em Java, utilizando os conceitos de Programação Orientada a Objetos (POO).

O objetivo é administrar os recursos da cidade, desenvolver sua economia, aumentar seu prestígio e evoluir na hierarquia da nobreza até alcançar o título de Rei ou Rainha.

## Tecnologias utilizadas

- Java 21
- Visual Studio Code
- Git
- GitHub
- PlantUML

## Funcionalidades

- Partidas de um a quatro jogadores.
- Administração dos recursos da cidade.
- Compra e venda de grãos e terras.
- Colheita e distribuição de alimentos.
- Arrecadação de impostos.
- Definição da taxa de imposto e do nível de justiça.
- Construções e recrutamento de soldados.
- Controle de turnos e avanço dos anos.
- Cálculo de prestígio.
- Promoções de títulos de nobreza.
- Classificação dos participantes.
- Determinação do vencedor.
- Testes automatizados.

## Estrutura do projeto

    projeto-bolonha-e-milano/
    |-- src/
    |   |-- bolonhaemilano/
    |       |-- Main.java
    |       |-- Menu.java
    |       |-- Jogador.java
    |       |-- Cidade.java
    |       |-- Mercado.java
    |       |-- Agricultura.java
    |       |-- Governo.java
    |       |-- Construcoes.java
    |       |-- Nobreza.java
    |       |-- Partida.java
    |       |-- Testes.java
    |-- uml/
    |   |-- Diagrama-de-classes.puml
    |   |-- Diagrama-de-sequencia.puml
    |-- casos-de-uso/
    |   |-- Diagrama-casos-de-uso.puml
    |-- .gitignore
    |-- README.md

## Requisitos

- Java JDK 21.
- Terminal ou ambiente de desenvolvimento Java.

Não é necessário instalar bibliotecas externas para executar o jogo.

## Como compilar

Abra o terminal na pasta principal do projeto e execute:

    javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java

## Como executar

Após a compilação, execute:

    java -cp bin bolonhaemilano.Main

## Como jogar

Selecione a opção 1 para iniciar uma partida e informe a quantidade de jogadores e seus respectivos nomes.

Cada participante administra uma cidade e utiliza as opções disponíveis no menu principal.

### Menu principal

- 1 - Iniciar novo jogo
- 2 - Ver status da cidade
- 3 - Mercado
- 4 - Agricultura
- 5 - Governo
- 6 - Construções e soldados
- 7 - Encerrar turno
- 8 - Ver classificação
- 9 - Sobre o jogo
- 0 - Sair

### Recursos iniciais

Cada cidade começa no ano de 1400 com:

- 1000 florins.
- 10000 hectares de terra.
- 500 grãos.
- 200 servos.
- 0 soldados.
- 0 construções.
- Taxa de imposto de 10%.
- Nível de justiça moderado.

### Turnos e condições de vitória

O ano avança quando todos os participantes encerram seus turnos.

A colheita e a arrecadação de impostos podem ser realizadas uma vez por ano.

Ao final de cada ano, o sistema verifica o prestígio dos participantes e suas possíveis promoções.

A hierarquia da nobreza é composta pelos seguintes títulos:

| Título | Prestígio mínimo |
|---|---:|
| Cavaleiro | 0 |
| Barão | 400 |
| Conde | 550 |
| Marquês | 700 |
| Duque | 850 |
| Rei/Rainha | 1000 |

A partida termina quando um participante alcança o título máximo ou quando o limite de anos é atingido.

O vencedor é determinado pelo maior título de nobreza e, em caso de empate, pelo maior prestígio.

## Testes automatizados

Após compilar o projeto, execute:

    java -cp bin bolonhaemilano.Testes

Os testes verificam operações válidas e inválidas, gerenciamento de recursos, regras anuais, promoções, turnos e condições de vitória.

### Resultado obtido

| Resultado | Quantidade |
|---|---:|
| Testes aprovados | 61 |
| Testes reprovados | 0 |
| Total | 61 |

Todos os testes automatizados foram aprovados.

## Documentação UML

O projeto possui três diagramas desenvolvidos em PlantUML:

- [Diagrama de classes](uml/Diagrama-de-classes.puml)
- [Diagrama de casos de uso](casos-de-uso/Diagrama-casos-de-uso.puml)
- [Diagrama de sequência](uml/Diagrama-de-sequencia.puml)

## Etapas de desenvolvimento

- Etapa 1: Estrutura inicial, menu e jogador.
- Etapa 2: Classe Cidade e gerenciamento de recursos.
- Etapa 3: Mercado e economia.
- Etapa 4: Agricultura e população.
- Etapa 5: Impostos e governo.
- Etapa 6: Construções e soldados.
- Etapa 7: Rodadas, múltiplos jogadores e vitória.
- Etapa 8: Integração, testes e entrega final.

## Conceitos de programação aplicados

- Classes e objetos.
- Encapsulamento.
- Construtores.
- Métodos e atributos.
- Constantes.
- Associação entre classes.
- Listas de objetos.
- Validação de dados.
- Modularização.
- Refatoração.
- Testes automatizados.

## Autoria

Lorena Galrão de França

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos.
