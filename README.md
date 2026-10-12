# Calculadora Interativa em Java

Uma calculadora de console desenvolvida em Java com foco em arquitetura limpa e usabilidade. O sistema suporta operações matemáticas fundamentais, mantém um histórico dinâmico da sessão e implementa validações rigorosas para evitar interrupções inesperadas na execução.

## Funcionalidades
* **Operações Matemáticas:** Adição, subtração, multiplicação e divisão.
* **Histórico de Sessão:** Registro ilimitado e sequencial de todos os cálculos realizados na execução atual.
* **Validação Contínua:** Tratamento de entradas inválidas e navegação à prova de falhas (menu via `String`).
* **Suporte a Múltiplos Locales:** Conversão automática de vírgula para ponto, garantindo que o sistema processe decimais independentemente da configuração de idioma do sistema operacional do usuário.

## Decisões de Arquitetura e Engenharia

O desenvolvimento deste projeto foi pautado em práticas sólidas de engenharia de software para garantir escalabilidade e estabilidade:

* **Prevenção de Falhas e Separação de Responsabilidades:** 
  A lógica de cálculo foi isolada da interface de usuário. Exceções matemáticas, como a divisão por zero, não são tratadas de forma silenciosa ou apenas impressas na camada de domínio. O método de cálculo lança uma `ArithmeticException`, delegando o tratamento para a camada superior (Main). Essa medida preventiva blinda o núcleo do sistema e garante que falhas lógicas não gerem comportamentos anômalos.

* **Gerenciamento de Estado Dinâmico:** 
  Estruturas de memória estáticas foram descartadas em favor de coleções dinâmicas da API do Java (`List<String>` com `ArrayList`). Isso elimina limites engessados de armazenamento, permitindo que o usuário realize e consulte um volume ilimitado de contas durante a sessão sem risco de vazamento de memória ou estouro de índices.

* **Experiência de Usuário (UX) via CLI:** 
  Sistemas de terminal tendem a ser abruptos, despejando blocos de texto instantaneamente. Para contornar isso, foi projetado um controle de fluxo temporal utilizando pausas lógicas na thread de execução (`Thread.sleep`). Esse controle dita o ritmo do console, oferecendo um tempo de leitura confortável para que o usuário processe os resultados e detalhes no histórico antes de ser bombardeado por novos menus de decisão. A legibilidade é reforçada pelo uso do `DecimalFormat`, que higieniza os dados de saída ocultando zeros decimais desnecessários.

## Tecnologias Utilizadas
* **Linguagem:** Java (JDK)
* **Bibliotecas Base:** 
  * `java.util.Scanner` (I/O)
  * `java.util.List` / `java.util.ArrayList` (Estruturas de Dados)
  * `java.text.DecimalFormat` (Formatação e Sanitização)
