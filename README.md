# Desafio Técnico - Target Sistemas

Resolvi os 3 exercícios em Java, sem usar nenhuma biblioteca externa. Só precisa ter o JDK instalado (versão 11 ou maior).

## Como rodar

Abra o terminal na pasta do projeto e compile:

```
javac *.java
```

Depois rode o exercício que quiser:

```
java Comissao
java Estoque
java Juros
```

## Exercício 1 - Comissão (`Comissao.java`)

Lê as vendas do arquivo `vendas.json` e calcula a comissão de cada venda:

- Abaixo de R$ 100,00: sem comissão
- Abaixo de R$ 500,00: 1%
- A partir de R$ 500,00: 5%

Depois soma tudo por vendedor e mostra o resultado:

```
João Silva: R$ 495.68
Maria Souza: R$ 465.95
Carlos Oliveira: R$ 379.37
Ana Lima: R$ 404.98
```

## Exercício 2 - Estoque (`Estoque.java`)

Carrega os produtos do `estoque.json` e mostra um menu para lançar entrada ou saída de mercadoria. Cada movimentação tem um ID (número sequencial) e uma descrição. Depois de cada lançamento, o programa mostra o estoque final do produto.

O programa também não deixa fazer saída maior do que o estoque disponível.

## Exercício 3 - Juros (`Juros.java`)

Pede o valor e a data de vencimento e calcula os juros até hoje, considerando 2,5% ao dia:

```
juros = valor * 0.025 * dias de atraso
```

Se a conta ainda não venceu, os juros são zero.

## Observação

Para ler os arquivos JSON usei expressões regulares (`Pattern`/`Matcher`), para não depender de bibliotecas externas como Gson ou Jackson.
