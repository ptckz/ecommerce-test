# Java Spring E-commerce
Aplicação REST API baseada em Java Spring, Spring Boot, Hibernate ORM e H2(in-memory). Vai ser pré-recarregado uma pequena base de dados chamado `data.sql` que se encontra em `resources/` ao iniciar a aplicação.

# REST API Endpoints

Todas as entradas e saídas utilize JSON
```
/product
    GET / - Lista todos os produtos
    POST / - Criar um novo produto. Necessário: String name, Int price.
    DELETE /{id} - Deleta o produto com id
    PUT /{id} - Atualiza o Produto. Necessário: String name, Int price.
    
/cart
    GET / - Cria uma novo carrinho
    GET /{id} - Retorna o estado atual do carrinho
    GET /amount/{id} - Retorna o preço total do carrinho.
    POST /{idCart}/{idProduto}/{quantity} - Adiciona um produto e sua quantidade no carrinho
    PUT /{idCart}/{idProduto}/{quantity} - Atualiza a quantidade do produto no carrinho
    DELETE /{idCart}/{idProduto} - Deleta um determinado produto no carrinho
```

# Start

Instale as dependências do projeto.
```sh
$ mvn install
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




