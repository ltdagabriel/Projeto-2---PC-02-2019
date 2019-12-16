 ### API Twitter busca usando Threads
 
 - Coletar tweets segundo um critério de pesquisa via API Searching e API Streaming.
 - Processar em tempo real os tweets coletados usando threads. 
 - Para cada tweet:
    - Armazenar em um arquivo .json o tweet completo. 
    - Realizar a tokeniza¸c˜ao do tweet e armazenar id do tweet, 
    [usuário, data, texto original, texto tokenizado]. 
    - Realizar a traduc¸˜ao da URL curta para longa.(URLEntitie)
    - Fazer download do site apontado pela URL.
 - Implementar um mecanismo de cache para evitar realizar traduçãao de uma URL que já foi traduzida (URLEntitie)