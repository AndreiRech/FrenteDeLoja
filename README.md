# FrenteDeLoja
Programa que simula o funcionamente de uma loja

DESCRIÇÃO:

O trabalho 1 da disciplina consiste no desenvolvimento das classes
para um programa Frente de Loja, conforme modelagem realizada em
aula (ver diagrama de classes no documento anexo). Para validar a
sua implementação, crie uma classe de teste contendo um método
main(). Essa classe deve testar/validar todos os métodos de todas as
classes implementadas. Importante: a interface pública das classes
deve respeitar o que foi modelado no diagrama de classes.

A loja vende diversos tipos de produtos. O catálogo de produtos
mantém a relação de produtos com os quais a loja trabalha ou já
trabalhou. Sobre cada produto, armazena-se o código (int), a
descrição (String) e o preço unitário (double). A empresa trabalha
com um estoque centralizado. O sistema de estoque armazena para
cada produto a quantidade disponível no estoque. Antes de qualquer
venda é necessário consultar o estoque para ver a disponibilidade do
produto. A loja não limita a quantidade de produtos por cliente (desde
que exista disponibilidade no estoque). Uma venda pode incluir vários
itens diferentes. Para cada item informa-se a quantidade desejada e
calcula-se o valor do item. O vendedor pode oferecer até 10% de
desconto sobre o valor total da venda para vendas acima de R$ 250.
Ao final da venda deve-se acrescentar 25% de imposto e calcular o
valor final da venda.

Uma vez a venda efetivada é necessário dar baixa no estoque. A loja
deve manter o registro de todas as vendas para eventual consulta
futura e geração de relatórios.

-----------------------------------------------------------------------------

PARA UM MELHOR APROVEITAMENTO:

Para melhor aproveitamento do mesmo, digite o que foi pedido da forma pedida
(EX: FUNCIONARÁ [S / N] -> N / NÃO FUNCIONARA [S / N] -> nao/n)

Também, ao concluir uma venda no menu do cliente, é aconselhado que saia do 
mesmo e vá para o menu de seleção de usuário antes de realizar outra venda.

Por fim, adicione primeiro os produtos no menu de gerente antes de qualquer 
outra função.
