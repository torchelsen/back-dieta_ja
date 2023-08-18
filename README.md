# back-dieta_ja
Backend do sistema em desenvolvimento para a plataforma Dieta Já.

-------------
# DESIGN DE CLASSES

## App.java
### Atributos
- leitor (Scanner): Utilizado para ler dados de entrada do usuário.
- listaPessoas (ArrayList<Pessoa>): Armazena as instâncias de pessoas cadastradas.
- listaAlimentos (ArrayList<Alimento>): Armazena as instâncias de alimentos cadastrados.
- pessoaLogada (Pessoa): Armazena a pessoa que está atualmente logada no sistema.
### Métodos:
- main(String[] args): Método principal que inicia a execução do programa.
- mostraMenuNaoLogado(): Exibe o menu de seleções para um usuário não logado.
- mostraMenuUsuarioComum(): Exibe o menu de seleções para um usuário comum logado.
- mostraMenuAdministrador(): Exibe o menu de seleções para um administrador logado.
- cadastrarUserComum(): Cria um novo usuário comum e o adiciona à lista de pessoas.
- cadastrarAdmin(): Cria um novo administrador e o adiciona à lista de pessoas.
- login(): Realiza o login de um usuário através da autenticação do email e senha.
- logout(): Realiza o logout do usuário atualmente logado.
- editaPropriaPessoa(): Permite que um usuário comum edite seus próprios dados cadastrais.
- excluiPropriaPessoa(): Permite que um usuário comum exclua seu próprio cadastro.
- criarDieta(): Cria uma nova dieta e a associa à pessoa logada.
- listarDietas(): Lista todas as dietas associadas à pessoa logada.
- listaPessoas(): Lista todas as pessoas cadastradas no sistema (apenas para administradores).
- editaPessoa(): Permite que um administrador edite os dados cadastrais de uma pessoa.
- excluiPessoa(): Permite que um administrador exclua o cadastro de uma pessoa.
- cadastrarAlimento(): Cria um novo alimento e o adiciona à lista de alimentos.
- listaAlimentos(): Lista todos os alimentos cadastrados no sistema.
- editaAlimento(): Permite que um administrador edite os dados de um alimento.
- excluiAlimento(): Permite que um administrador exclua um alimento cadastrado.
- obterRefeicoes(): Método fictício que obtém as refeições desejadas para uma dieta.

## Classe Pessoa:
### Atributos:
- id: Long - Identificador único da pessoa.
- nome: String - Nome da pessoa.
- idade: int - Idade da pessoa.
- sexo: String - Sexo da pessoa.
- peso: double - Peso da pessoa.
- altura: int - Altura da pessoa.
- email: String - Email da pessoa.
- senha: String - Senha da pessoa.
- taxaMetabolismoBasal: double - Taxa metabólica basal da pessoa, calculada com base em outros atributos.
- dietas: List<Dieta> - Lista de dietas associadas à pessoa.
### Métodos:
- Pessoa() - Construtor vazio da classe Pessoa.
- Pessoa(nome: String, idade: int, sexo: String, peso: double, altura: int, email: String, senha: String) - - Construtor que recebe os dados da pessoa para inicialização.
- Pessoa(nome: String, email: String, senha: String) - Construtor que recebe os dados básicos da pessoa (utilizado para administradores).
- addDieta(dieta: Dieta): void - Adiciona uma dieta à lista de dietas da pessoa.
- getDietas(): List<Dieta> - Retorna a lista de dietas associadas à pessoa.
- getId(): Long - Retorna o ID da pessoa.
- getNome(): String - Retorna o nome da pessoa.
- getIdade(): int - Retorna a idade da pessoa.
- getSexo(): String - Retorna o sexo da pessoa.
- getPeso(): double - Retorna o peso da pessoa.
- getAltura(): int - Retorna a altura da pessoa.
- getEmail(): String - Retorna o email da pessoa.
- getSenha(): String - Retorna a senha da pessoa.
- getTaxaMetabolismoBasal(): Double - Retorna a taxa metabólica basal da pessoa.
- setId(id: Long): void - Define o ID da pessoa.
- setNome(nome: String): void - Define o nome da pessoa.
- setIdade(idade: int): void - Define a idade da pessoa.
- setSexo(sexo: String): void - Define o sexo da pessoa.
- setPeso(peso: double): void - Define o peso da pessoa.
- setAltura(altura: int): void - Define a altura da pessoa.
- setEmail(email: String): void - Define o email da pessoa.
- setSenha(senha: String): void - Define a senha da pessoa.
- toString(): String - Retorna uma representação em String dos atributos da pessoa.
- calcularTaxaMetabolismoBasal(): double - Calcula e retorna a taxa metabólica basal da pessoa.

## Classe PessoaDAO:
### Métodos:
- salvarPessoa(Pessoa pessoa)
- buscarPessoaPorId(Long id)
- buscarPessoaPorEmail(String email)
- buscarTodasPessoas(): List<Pessoa>
- atualizarPessoa(Pessoa pessoa)
- excluirPessoa(Pessoa pessoa)

## Classe Alimento:
### Atributos:
- id (int): identificador único do alimento (gerado automaticamente pelo banco de dados)
- nome (String): o nome do alimento
- caloriasPor100g (double): a quantidade de calorias por 100g do alimento
- carboidratosPor100g (double): a quantidade de carboidratos por 100g do alimento
- proteinasPor100g (double): a quantidade de proteínas por 100g do alimento
- gordurasPor100g (double): a quantidade de gorduras por 100g do alimento
- valorAlimento (double): o valor pago no alimento
### Métodos:
- getters e setters para todos os atributos

## Classe AlimentoDAO:
### Métodos:
- salvarAlimento(Alimento alimento)
- buscarAlimentoPorId(Long id)
- buscarTodosAlimentos(): List<Alimento>
- buscarTodosAlimentoas(): List<Alimento>
- atualizarAlimento(Alimento alimento)
- excluirAlimento(Alimento alimento)
- 
## Classe Dieta:
### Atributos:
- id (int): identificador único da dieta (gerado automaticamente pelo banco de dados)
- totalCalorias (double): total de calorias da dieta
- totalCarboidratos (double): total de carboidratos da dieta
- totalProteinas (double): total de proteinas da dieta
- totalGorduras (double): total de gorduras da dieta
- totalValor (double): total de valor da dieta
- pessoa (Pessoa): a pessoa para quem a dieta está sendo criada
- alimentos (List<Alimento>): a lista de alimentos da dieta
### Métodos:
- getters e setters para todos os atributos
- adicionarAlimento: adiciona um alimento à lista de alimentos da dieta
- atualizaTotais: atualiza as quantidades totais (calorias, carboidratos, proteínas e gorduras) na dieta atual após a inclusão de uma refeição

-----------------------------------

# Query MySQL
- create database bancodietas;
- drop database bancodietas;

- SELECT * FROM bancodietas.pessoa;
- SELECT * FROM bancodietas.alimento;
- SELECT * FROM bancodietas.dieta;