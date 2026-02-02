# Luta-de-Classes
Repositório dedicado ao projeto final da matéria de POO, sendo um jogo de terminal com implementação de interface gráfica minimalista, feito completamente em Java, sem nenhuma biblioteca externa.

# Dependências
Para este projeto rodar, é necessário apenas:
* Java na versão 17 (java-17-openjdk) ou superior
* Make
* Sistema GNU/Linux, preferencialmente

# Sobre o Makefile
O Makefile presente nesse repositório tem como objetivo compilar e rodar o programa numa só linha de comando. Ao todo, existem 4 comandos nessa Makefile, a saber:
* `all`: roda todos os outros 3 comandos, na ordem em que aparecem abaixo
* `clean`: limpa a pasta bin, contendo os arquivos .class e o programa compilado, se existirem
* `compile`: cria a pasta bin caso ela não exista, compila o jogo e manda todos os .class e o programa compilado pra lá
* `run`: roda o programa

Para maior conveniência, apenas utilize o seguinte comando dentro da pasta raiz:
```jsonc
make
```

# Sobre as mecânicas do jogo
Você primeiro seleciona número de combatentes e o range de nível de ambas as parties. Caso escolha 5, obrigatoriamente terá um personagem para cada job na sua equipe! Os jobs apenas se repetem quando você tem ao menos um de cada em sua party.
Cada job possui uma lista de habilidades especiais que podem ser usadas para garantir vantagens ou destruir oponentes.
Cada personagem possui pontos de força, agilidade, vitalidade, destreza, inteligência e sorte, que influenciam em diversas mecânicas do jogo, além de Pontos de Vida (HP) e Mana (MP).
O jogo só acaba quando o último combatente de uma das equipes morrer. Boa sorte!
