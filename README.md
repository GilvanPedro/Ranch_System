# Ranch System: Sistema de Gestão Rural 🚜

![Java](https://img.shields.io/badge/Java-17+-%23007396.svg?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-%23C71A36.svg?style=for-the-badge&logo=apache-maven&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java%20Swing-GUI-%235382A1.svg?style=for-the-badge&logo=java&logoColor=white)
![Gson](https://img.shields.io/badge/Gson-2.10.1-%23F44336.svg?style=for-the-badge&logo=google&logoColor=white)
![License](https://img.shields.io/github/license/GilvanPedro/Ranch_System?style=for-the-badge&color=blue)

## 🌟 Visão Geral do Projeto

Bem-vindo ao **Ranch System**, uma solução de software inovadora e robusta, meticulosamente desenvolvida em Java para revolucionar a gestão de propriedades rurais. Este sistema desktop foi concebido para ser o pilar da eficiência operacional em fazendas modernas, oferecendo um controle sem precedentes sobre os pilares fundamentais de qualquer empreendimento rural: **funcionários, rebanhos, plantações e finanças**.

Com uma interface gráfica intuitiva e responsiva, construída com o poder do Java Swing, o Ranch System transforma complexas tarefas administrativas em processos simplificados e acessíveis. Nosso objetivo é capacitar gestores rurais a tomar decisões estratégicas baseadas em dados precisos e atualizados, impulsionando a produtividade e a sustentabilidade de suas operações. Prepare-se para uma gestão rural inteligente e integrada!

## 🚀 Funcionalidades Essenciais

O Ranch System integra um conjunto de módulos poderosos, cada um projetado para atender às necessidades específicas da gestão de uma fazenda:

### 👨‍🌾 Gestão de Funcionários

Gerencie sua equipe com facilidade e precisão. Este módulo permite:

*   **Cadastro Detalhado:** Registre e mantenha informações cruciais de cada colaborador, incluindo nome completo, CPF, salário e dados de contato.
*   **Validação Inteligente:** Garanta a integridade dos dados com validações automáticas de CPF e telefone, prevenindo erros e duplicidades.
*   **Controle Completo (CRUD):** Realize operações de Criação, Leitura, Atualização e Exclusão de registros de funcionários de forma eficiente.
*   **Persistência Segura:** Todos os dados dos funcionários são armazenados de forma estruturada e segura em arquivos JSON.

### 🐄 Gestão de Gado

Monitore seu rebanho com um sistema de rastreamento individualizado, oferecendo:

*   **Registro Individual:** Cadastre cada animal com detalhes como nome, data de nascimento, raça, proprietário, série, RGN (Registro Geral do Animal), categoria, número de filhos e sexo.
*   **Unicidade de RGN:** O sistema assegura que cada animal possua um RGN único, evitando registros duplicados e facilitando o controle.
*   **Operações CRUD:** Gerencie o ciclo de vida completo do seu rebanho, desde o nascimento até a saída, com funcionalidades CRUD intuitivas.
*   **Dados Estruturados:** As informações do rebanho são persistidas em arquivos JSON, garantindo fácil acesso e organização.

### 🌾 Gestão de Plantações

Otimize suas culturas com um controle detalhado e projeções inteligentes:

*   **Registro Abrangente:** Cadastre suas plantações especificando o tipo de cultura, data de plantio, área em hectares e se há uso de agrotóxicos.
*   **Previsão de Colheita:** O sistema calcula automaticamente a data estimada de colheita, baseando-se no tipo de cultura e seus respectivos ciclos de crescimento.
*   **Validação de Culturas:** Garanta a consistência dos dados com validações para tipos de plantação e áreas cultivadas.
*   **Operações CRUD:** Adicione, visualize, edite e remova registros de plantações com agilidade.
*   **Persistência em JSON:** Mantenha um histórico organizado de todas as suas plantações em arquivos JSON.

### 💰 Gestão Financeira

Tenha total controle sobre as finanças da sua fazenda, com recursos para:

*   **Controle de Fluxo:** Registre todas as movimentações financeiras, categorizando-as como 'GASTO' ou 'GANHO'.
*   **Categorização Flexível:** Associe cada transação a categorias específicas (Gado, Funcionário, Plantação, Consertos, Contratação, Outros) para uma análise detalhada.
*   **Saldo em Tempo Real:** Obtenha uma visão clara da saúde financeira da fazenda com o cálculo do saldo financeiro total.
*   **Validação Financeira:** O sistema verifica a validade das descrições, tipos, categorias e valores, assegurando a precisão dos registros.
*   **Operações CRUD:** Gerencie todas as suas transações financeiras com operações CRUD completas.
*   **Dados Financeiros em JSON:** Todas as transações são armazenadas de forma organizada em arquivos JSON.

## 🛠 Tecnologias Utilizadas

O Ranch System é construído sobre uma base tecnológica sólida e comprovada, garantindo desempenho, segurança e manutenibilidade:

| Tecnologia       | Versão Principal | Descrição                                                                                               |
| :--------------- | :--------------- | :------------------------------------------------------------------------------------------------------ |
| **Java**         | 17+              | Linguagem de programação robusta e multiplataforma, essencial para a lógica de negócios do sistema.      |
| **Apache Maven** | 3.8+             | Ferramenta poderosa para automação de build, gerenciamento de dependências e padronização do projeto.   |
| **Java Swing**   | -                | Toolkit para o desenvolvimento da interface gráfica (GUI) desktop, proporcionando uma experiência rica. |
| **Gson**         | 2.10.1           | Biblioteca Java da Google para serialização e desserialização de objetos Java para JSON, utilizada para persistência de dados de forma eficiente. |

## 🏛 Arquitetura do Sistema

O projeto segue um padrão arquitetural bem definido para garantir modularidade, escalabilidade e facilidade de manutenção. Embora seja uma aplicação desktop, a organização interna reflete princípios de design de software robustos:

### Padrão MVC (Model-View-Controller)

O Ranch System adota o padrão MVC para separar as responsabilidades da aplicação, promovendo um código mais limpo e organizado:

*   **Model (`Entity`):** Representa os dados e a lógica de negócios. As classes no pacote `br.com.RanchSystem.Entity` (e.g., `Funcionario`, `Gado`, `Plantacao`, `Despesa`) encapsulam os atributos e comportamentos dos objetos do domínio.
*   **View (`View`):** Responsável pela apresentação dos dados ao usuário. As classes no pacote `br.com.RanchSystem.View` (e.g., `MainView`) utilizam Java Swing para construir a interface gráfica, exibindo informações e capturando interações do usuário.
*   **Controller (`Controller`):** Atua como intermediário entre o Model e a View, processando as entradas do usuário, atualizando o Model e selecionando a View apropriada para exibição. As classes no pacote `br.com.RanchSystem.Controller` (e.g., `FuncionarioController`, `GadoController`) gerenciam a lógica de aplicação e a persistência dos dados.

### Persistência de Dados em JSON

A persistência dos dados é realizada através de arquivos JSON, utilizando a biblioteca Gson. Cada módulo (Funcionários, Gado, Plantações, Despesas) possui seu próprio arquivo JSON dedicado (`Arquivos/funcionarios.json`, `Arquivos/gados.json`, etc.), garantindo uma separação clara e um formato de dados legível. Os `Controllers` são responsáveis por ler e escrever nesses arquivos, convertendo objetos Java para JSON e vice-versa.

## 📁 Estrutura do Projeto

A organização dos diretórios e arquivos do projeto é intuitiva, facilitando a navegação e o entendimento do código:

```
Ranch_System/
├── RanchSystem/                  # Diretório raiz do projeto Maven
│   ├── Arquivos/                 # 🗄 Armazena os arquivos JSON para persistência de dados
│   │   ├── despesas.json         # Dados financeiros
│   │   ├── funcionarios.json     # Dados dos funcionários
│   │   ├── gados.json            # Dados do rebanho
│   │   └── plantacoes.json       # Dados das plantações
│   ├── src/                      # Código fonte da aplicação
│   │   └── main/
│   │       └── java/
│   │           └── br/
│   │               └── com/
│   │                   └── RanchSystem/
│   │                       ├── Controller/       # 🧠 Lógica de controle e manipulação de dados (CRUD, validações)
│   │                       ├── Entity/           # 📦 Classes de entidade (modelos de dados do domínio)
│   │                       ├── Logicas/          # ⚙️ Classes com lógicas de validação e regras de negócio específicas
│   │                       └── View/             # 🖥 Classes da interface gráfica (Java Swing) e interação com o usuário
│   ├── pom.xml                   # ⚙️ Arquivo de configuração do Apache Maven (dependências, plugins, build)
│   └── target/                   # 📦 Diretório de saída do build (JARs compilados, classes, etc.)
├── LICENSE                       # ⚖️ Arquivo de licença do projeto (MIT License)
└── README.md                     # 📄 Este documento detalhado do projeto
```

## ▶️ Como Iniciar

Para colocar o Ranch System em funcionamento em sua máquina, siga estas instruções detalhadas:

### Pré-requisitos Essenciais

Antes de prosseguir, certifique-se de que os seguintes softwares estejam instalados e configurados em seu ambiente de desenvolvimento:

*   **Java Development Kit (JDK) 17 ou superior:** O ambiente de execução e desenvolvimento Java é fundamental. Você pode baixá-lo no [site oficial da Oracle](https://www.oracle.com/java/technologies/downloads/).
*   **Apache Maven:** Essencial para gerenciar as dependências do projeto e automatizar o processo de build. Faça o download e configure-o a partir do [site oficial do Maven](https://maven.apache.org/download.cgi).
*   **Uma IDE (Ambiente de Desenvolvimento Integrado):** Embora não seja estritamente obrigatório, o uso de uma IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/) ou [VS Code](https://code.visualstudio.com/download) com as extensões Java e Maven apropriadas é **altamente recomendado** para uma experiência de desenvolvimento e execução mais fluida.

### Guia de Instalação e Execução

1.  **Clonar o Repositório:**

    Abra seu terminal ou prompt de comando e execute o seguinte comando para clonar o projeto para sua máquina local:

    ```bash
    git clone https://github.com/GilvanPedro/Ranch_System.git
    cd Ranch_System/RanchSystem
    ```

2.  **Compilar o Projeto com Maven:**

    Navegue até o diretório `RanchSystem` (onde o arquivo `pom.xml` está localizado) e utilize o Maven para compilar o projeto e resolver suas dependências:

    ```bash
    mvn clean install
    ```

    Este comando irá baixar todas as bibliotecas necessárias (incluindo Gson), compilar o código-fonte e empacotar a aplicação em um arquivo JAR executável, que será encontrado na pasta `target/`.

3.  **Executar a Aplicação Desktop:**

    Após a compilação bem-sucedida, você pode iniciar a aplicação Ranch System diretamente do terminal:

    ```bash
    java -jar target/RanchSystem-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

    **Alternativa via IDE:** Se você estiver utilizando uma IDE, localize a classe principal `br.com.RanchSystem.Main` e execute-a diretamente. A interface gráfica do Ranch System será exibida.

## 🗺️ Roadmap: Futuro do Ranch System

O Ranch System está em constante evolução. As seguintes melhorias e expansões estão no horizonte para futuras versões, visando tornar a gestão rural ainda mais poderosa e integrada:

*   **Integração com Banco de Dados Relacional:** Migração da persistência de dados de arquivos JSON para um sistema de gerenciamento de banco de dados robusto (e.g., PostgreSQL, MySQL). Isso garantirá maior escalabilidade, integridade e capacidade de consulta de dados.
*   **Módulo de Relatórios Avançados:** Desenvolvimento de um sistema de relatórios mais dinâmico e personalizável, com opções de exportação para formatos como PDF e Excel, permitindo análises aprofundadas.
*   **Sistema de Autenticação e Autorização:** Implementação de um módulo de segurança com múltiplos usuários e níveis de acesso, ideal para equipes de fazenda com diferentes responsabilidades.
*   **Versão Web da Aplicação:** Expansão do Ranch System para uma plataforma web, utilizando tecnologias como Spring Boot para o backend e frameworks modernos de frontend (React, Angular, Vue.js), possibilitando acesso remoto e mobilidade.
*   **Monitoramento em Tempo Real (IoT):** Integração com dispositivos IoT e sensores para coletar dados em tempo real sobre condições climáticas, saúde do rebanho, umidade do solo, etc., oferecendo insights preditivos.

## 🤝 Como Contribuir

Sua contribuição é muito valiosa para o crescimento do Ranch System! Se você tem ideias, encontrou um bug ou deseja adicionar uma nova funcionalidade, siga estas diretrizes para contribuir:

1.  **Faça um Fork** do repositório para sua conta GitHub.
2.  **Crie uma Nova Branch** para sua feature ou correção: `git checkout -b feature/sua-nova-feature` ou `git checkout -b bugfix/correcao-de-bug`.
3.  **Implemente suas Alterações:** Faça as modificações necessárias, adicione testes (se aplicável) e garanta que o código esteja limpo e bem documentado.
4.  **Commit suas Mudanças:** Escreva mensagens de commit claras e descritivas: `git commit -m 'feat: Adiciona nova funcionalidade X'` ou `git commit -m 'fix: Corrige bug Y'`.
5.  **Envie para sua Branch:** `git push origin feature/sua-nova-feature`.
6.  **Abra um Pull Request (PR):** Descreva detalhadamente suas alterações, o problema que resolve e como foi testado. Estamos ansiosos para revisar sua contribuição!

## ⚖️ Licença

Este projeto está sob a **Licença MIT**. Para detalhes completos sobre os termos e condições, consulte o arquivo [LICENSE](LICENSE) no repositório.

## 👤 Autores

*   **Gilvan Pedro** - Desenvolvedor Principal
