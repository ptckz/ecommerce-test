# Java Spring E-commerce
Aplicação REST API baseada em Java Spring, Spring Boot, Hibernate ORM e H2(in-memory)

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
    
