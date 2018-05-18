# Java Spring E-commerce
Aplicação REST API baseada em Java Spring, Spring Boot, Hibernate ORM e H2(in-memory). Vai ser pré-recarregado uma pequena base de dados chamado `data.sql` que se encontra em `resources/` ao iniciar a aplicação.

Aplicação está rodando em:
```sh
https://ecommerce-infra.herokuapp.com/api/v1/
```

# REST API Endpoints

Todas as entradas e saídas utilize JSON
```
/product
    GET / - Lista todos os produtos
    POST / - Criar um novo produto.
    DELETE /{id} - Deleta o produto com id
    PUT /{id} - Atualiza o Produto.
    
/cart
    GET / - Cria uma novo carrinho
    GET /{id} - Retorna o estado atual do carrinho
    GET /amount/{id} - Retorna o preço total do carrinho.
    POST /{idCart}/{idProduct}/{quantity} - Adiciona um produto e sua quantidade no carrinho
    PUT /{idCart}/{idProduct}/{quantity} - Atualiza a quantidade do produto no carrinho
    DELETE /{idCart}/{idProduct} - Deleta um determinado produto no carrinho
```

# Start
Instale as dependências do projeto e gere uma imagem.
```sh
$ sudo mvn install dockerfile:build
```
Rode este comando e irá iniciar a aplicação
```sh
$ docker run -p 3001:3001 -d springio/ecommerce
```
Para iteragir com os Endpoints use os seguintes Scripts:

**Endpoint de Produtos**

Lista todos os produtos.
```sh
$ curl http://localhost:3001/api/v1/product/
```
Retornar o produto.
```sh
$ curl http://localhost:3001/api/v1/product/<idProduct>
```
Criar um produto novo.
```sh
$ curl -H "Content-Type: application/json" -X POST -d '{"name":"Samsung S8","price":3460}' http://localhost:3001/api/v1/product/
```
Atualizando um determinado produto.
```sh
$ curl -H "Content-Type: application/json" -X PUT -d '{"name":"Samsung Galaxy S8","price":3460}' http://localhost:3001/api/v1/product/<id>
```
Deletando um determinado produto.
```sh
$ curl -X DELETE http://localhost:3001/api/v1/product/<idProduct>
```

**Endpoint do Carrinho de compras**

Cria uma nova carteira.
```sh
$ curl http://localhost:3001/api/v1/cart/
```
Adicionar um determinado produto em seu carinho.
```sh
$ curl -X POST http://localhost:3001/api/v1/cart/<idCart>/<idProduct>/<quantity>
```
Atualizar a quantidade de um determinado produto em seu carrinho.
```sh
$ curl -X PUT http://localhost:3001/api/v1/cart/<idCart>/<idProduct>/<quantity>
```
Retornar o valor total do carrinho de compras.
```sh
$ curl http://localhost:3001/api/v1/cart/amount/<idCart>
```
Deletar um determinado produto de carrinho de compras.
```sh
$ curl -X DELETE http://localhost:3001/api/v1/cart/<idCart>/<idProduct>
```




