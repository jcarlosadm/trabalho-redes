# Programa cliente do servidor de arquivos

## Requisitos

Última versão do Java 8.

## Importação

Para importar esse projeto no eclipse, basta escolher **File > Import ** e selecionar importar como projeto Maven. Depois navegue até a pasta desse projeto e complete a importação.

Para gerar o jar, pode-se usar um comando maven no terminal ou no eclipse:

*No terminal:*

```bash
mvn install

```

*No eclipse:*

Botão direito no projeto, **Run As > Maven Install**.

## Uso

Primeiro, crie um arquivo **general.config**. Copie o conteúdo do arquivo **general.config.model** e cole em **general.config**, e mude as configurações nesse último arquivo. As configurações são o IP e a porta do host.

No eclipse, basta executar o método **main** em **fileserver.Client**, localizado na pasta **src/main/java**.

Se estiver com o jar gerado, basta executar o jar.

Um aviso se for usar no eclipse: só há mascaramento de senha no terminal/cmd. Se usar o programa usando o console do eclipse, senhas digitadas serão mostradas na tela.