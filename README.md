# Mercado Fácil
 
Um supermercado da cidade de Campina Grande precisa de um sistema que gerencie o estoque e venda de produtos na sua loja. Neste sistema, o administrador deve obter uma visão geral e o controle sobre o funcionamento do supermercado, por exemplo, ele deve poder adicionar novos produtos, acompanhar quantas unidades do produto estão disponíveis, alterar preços, ser notificado sobre eventos críticos, gerenciar as vendas e oferecer alguns serviços personalizados para o cliente.

## User Stories já implementadas

- Eu, como administrador, gostaria de adicionar um novo produto no sistema,
informando seu nome, código de barra, fabricante, situação, e categoria;
- Eu, como administrador, gostaria de consultar a disponibilidade e o preço de cada
produto do supermercado;
- Eu, como administrador, gostaria de atribuir um preço (R$) a um determinado
produto no sistema;
- Eu, como administrador, gostaria de criar lotes associados aos produtos,
informando a quantidade de itens disponíveis e a data de validade.
- Eu, como USUÁRIO, gostaria de adicionar um novo produto no carrinho de compras,
informando o id do produto relacionado e a quantidade desejada do mesmo;
- Eu, como USUÁRIO, gostaria de remover um novo produto no carrinhos de compras, 
informando o id do produto relacionado e a quantidade desejada do mesmo;
- Eu, como USUÁRIO, gostaria de desistir da compra não precisando informar nada
- Eu, como USUÁRIO, gostaria de finalizar compra sem precisar informar nada para que isso seja feito.
- Eu, como USUÁRIO, gostaria de receber um registro dos produtos da minha compra e o, 
respectivo valor da compra, sem precisar informar nada.
- Eu, como USUÁRIO, gostaria de receber a lista dos produtos e o valor da compra antes mesmo de finalizar da compra sem precisar informar nada.
- Eu, como cliente do sistema Mercado Fácil, quero fazer pagamentos a partir de carrinhos de compras, para  que seja possível finalizar compras
- Eu, como cliente do sistema Mercado Fácil, quero selecionar a forma de pagamento (boleto, paypal e cartão de crédito) antes de finalizar compras, para que seja possível pagar as compras da forma mais conveniente
- Eu, como cliente do sistema Mercado Fácil, quero listar as últimas compras realizadas, para ter acesso a informações básicas das compras (código, data, itens e valor)
- Eu, como cliente do sistema Mercado Fácil, quero ter acesso aos detalhes de uma compra realizada, para acessar informações específicas sobre a compra em questão (código, data, itens, valor, forma de pagamento)
- Eu, como administrador do sistema Mercado Fácil, quero poder estabelecer acréscimos percentuais no valor da compra para cada forma de pagamento, para que o valor do pagamento esteja compatível com os custos operacionais de cada operação.
```
1 - O pagamento padrão é via boleto e não há acréscimo;
2 - Para pagamento em paypal há um acréscimo de 2% no valor da compra;
3 - Para pagamento em cartão de crédito há um acréscimo de 5% no valor da compra;
```
- Eu, como administrador do sistema Mercado Fácil, quero poder realizar operações CRUD para usuários padrão do sistema (clientes), para ter controle de cadastro e informações desses usuários.
- Eu, como administrador do sistema Mercado Fácil, quero poder estabelecer perfis de usuário padrão (normal, especial e premium), para possibilitar que descontos diferenciados sejam aplicados nas compras dos usuários.
```
1 - O usuário normal não tem desconto;
2 - O usuário especial tem um desconto de 10% para compras com mais de 10 produtos;
3- O usuário premium tem um desconto de 10% para compras com mais de 5 produtos.
````

## Estrutura básica

- Um projeto: MercadoFacil;
- Um Controller RestApiController que implementa os endpoints da API Rest.
- Dois repositórios são utilizados: ProdutoRepository e LoteRepository, que são responsáveis por manipular as entidades Produto e Lote em um banco de dados em memória;
- O modelo é composto pelas classes Produto.java e Lote.java que podem ser
encontradas no pacote model;
- O pacote exceptions guarda as classes de exceções que podem ser levantadas
dentro do sistema;
- Não há implementação de frontend, mas o projeto fornece uma interface de acesso à API via swagger.

## Tecnologias
Código base gerado via [start.sprint.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.3.3.RELEASE&packaging=jar&jvmVersion=1.8&groupId=com.example&artifactId=EstoqueFacil&name=EstoqueFacil&description=Projeto%20Estoque%20Facil&packageName=com.example.EstoqueFacil&dependencies=web,actuator,devtools,data-jpa,h2) com as seguintes dependências:  

- Spring Web
- Spring Actuator
- Spring Boot DevTools
- Spring Data JPA
- H2 Database

## Endereços úteis

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [http://localhost:8080/h2](http://localhost:8080/h2)



