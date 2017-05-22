# Programa servidor de arquivos

## Requisitos

Java 8 e ferramenta **keytool** do java.

## Importação

Para importar esse projeto no eclipse, basta escolher **File > Import** e selecionar importar como projeto Maven. Depois navegue até a pasta desse projeto e complete a importação.

Para gerar o jar, pode-se usar um comando maven no terminal ou no eclipse:

*No terminal:*

```bash
mvn install

```

*No eclipse:*

Botão direito no projeto, **Run As > Maven Install**.

## Uso

Primeiro, crie um arquivo **general.config**. Copie o conteúdo do arquivo **general.config.model** e cole em **general.config**, e mude as configurações nesse último arquivo. A única configuração necessária é a porta do servidor.

No eclipse, basta executar o método **main** em **fileserver.Server**, localizado na pasta **src/main/java**.

Se estiver com o jar gerado, basta executar o mesmo. É necessário ter o arquivo **general.config** na mesma pasta.

Um aviso se for usar no eclipse: só há mascaramento de senha no terminal/cmd. Se usar o programa usando o console do eclipse, senhas digitadas serão mostradas na tela.

Por último, execute esse programa primeiro antes de executar o programa cliente. O programa cliente também precisa saber o ip e a porta do servidor.